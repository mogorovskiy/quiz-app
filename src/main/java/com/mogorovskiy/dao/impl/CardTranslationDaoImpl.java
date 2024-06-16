package com.mogorovskiy.dao.impl;

import com.mogorovskiy.dao.CardDao;
import com.mogorovskiy.model.card.CardTranslation;
import com.mogorovskiy.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardTranslationDaoImpl implements CardDao<CardTranslation> {

    @Override
    public void createCard(CardTranslation card) {
        String sql = "INSERT INTO card_translation(deck_id, question, correct_answer) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, card.getDeckId());
            pstmt.setString(2, card.getQuestion());
            pstmt.setString(3, card.getCorrectAnswer());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting card: " + e);
        }
    }

    @Override
    public List<CardTranslation> getCardsByDeckId(Long deckId) {
        List<CardTranslation> cards = new ArrayList<>();
        String sql = "SELECT id, deck_id, question, correct_answer FROM card_translation WHERE deck_id = ?";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, deckId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Long fetchedDeckId = rs.getLong("deck_id");
                    String question = rs.getString("question");
                    String correctAnswer = rs.getString("correct_answer");
                    cards.add(new CardTranslation(id, fetchedDeckId, question, correctAnswer));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching cards by deckId: " + e);
        }
        return cards;
    }
}
