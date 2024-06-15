package com.mogorovskiy.dao.impl;

import com.mogorovskiy.dao.CardDao;
import com.mogorovskiy.model.card.CardTranslation;
import com.mogorovskiy.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
