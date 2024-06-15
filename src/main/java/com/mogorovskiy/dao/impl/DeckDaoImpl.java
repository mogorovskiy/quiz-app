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

public class DeckDaoImpl implements DeckDao {

    @Override
    public void createDeck(String name) {
        String sql = "INSERT INTO decks(name) VALUES(?)";
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

    public List<Deck> getDecksByName(String name) {
        String sql = "SELECT * FROM deck WHERE name = ?";
        List<Deck> decks = new ArrayList<>();
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
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
    public Deck getDeckById(int deckId) {
        String sql = "SELECT * FROM deck WHERE id = ?";
        Deck deck = null;
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, deckId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                deck = new Deck();
                deck.setId(rs.getLong("id"));
                deck.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deck;
    }
}
