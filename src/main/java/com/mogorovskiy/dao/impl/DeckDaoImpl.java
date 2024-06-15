package com.mogorovskiy.dao.impl;

import com.mogorovskiy.dao.DeckDao;
import com.mogorovskiy.util.DatabaseUtil;
import com.mogorovskiy.model.Deck;
import com.mogorovskiy.model.card.Card;
import com.mogorovskiy.model.card.CardMultipleChoice;

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
    public List<Card> getCardsByDeckId(Long deckId) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT id, question, answer_options, correct_option FROM cards WHERE deck_id = ?";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, deckId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String question = rs.getString("question");
                String[] answerOptions = rs.getString("answer_options").split(",");
                int correctOption = rs.getInt("correct_option");

                CardMultipleChoice card = new CardMultipleChoice(id, question, answerOptions, correctOption);
                card.setDeckId(deckId);
                cards.add(card);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return cards;
    }

    @Override
    public List<Deck> getAllDecks() {
        String sql = "SELECT * FROM decks";
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
            System.out.println(e.getMessage());
        }
        return decks;
    }

    public List<Deck> getDecksByName(String name) {
        String sql = "SELECT * FROM decks WHERE name = ?";
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
            System.out.println(e.getMessage());
        }
        return decks;
    }
}
