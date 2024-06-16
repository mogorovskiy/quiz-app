package com.mogorovskiy.dao.impl;

import com.mogorovskiy.dao.DeckDao;
import com.mogorovskiy.util.DatabaseUtil;
import com.mogorovskiy.model.Deck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeckDaoImpl implements DeckDao {

    @Override
    public void createDeck(String name) {
        String sql = "INSERT INTO deck(name) VALUES(?)";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Deck> getAllDecks() {
        String sql = "SELECT * FROM deck";
        List<Deck> decks = new ArrayList<>();
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Deck deck = new Deck();

                deck.setId(rs.getLong("id"));
                deck.setName(rs.getString("name"));

                decks.add(deck);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return decks;
    }

    @Override
    public Optional<Deck> getDeckById(Long deckId) {
        String sql = "SELECT * FROM deck WHERE id = ?";
        Deck deck = null;
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, deckId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                deck = new Deck();
                deck.setId(rs.getLong("id"));
                deck.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(deck);
    }
}
