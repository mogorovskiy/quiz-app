package com.mogorovskiy.dao.impl;

import com.mogorovskiy.dao.CardMultipleChoiceDao;
import com.mogorovskiy.util.DatabaseUtil;
import com.mogorovskiy.model.card.CardMultipleChoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardMultipleChoiceDaoImpl implements CardMultipleChoiceDao{

    @Override
    public void createCard(CardMultipleChoice card) {
        String sql = "INSERT INTO card_multiple_choice(deck_id, question, answer_options, correct_option) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, card.getDeckId());
            pstmt.setString(2, card.getQuestion());
            pstmt.setString(3, String.join(",", card.getAnswerOptions()));
            pstmt.setInt(4, card.getCorrectOption());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting card: " + e);
        }
    }
}
