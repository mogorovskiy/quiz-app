package com.mogorovskiy.dao.impl;

import com.mogorovskiy.dao.CardDao;
import com.mogorovskiy.util.DatabaseUtil;
import com.mogorovskiy.model.card.CardMultipleChoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardMultipleChoiceDaoImpl implements CardDao<CardMultipleChoice> {

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

    @Override
    public List<CardMultipleChoice> getCardsByDeckId(Long deckId) {
        String sql = "SELECT id, question, answer_options, correct_option FROM card_multiple_choice WHERE deck_id = ?";
        List<CardMultipleChoice> cards = new ArrayList<>();

        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, deckId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String question = rs.getString("question");
                String answerOptionsStr = rs.getString("answer_options");
                String[] answerOptions = answerOptionsStr.split(",");
                int correctOption = rs.getInt("correct_option");

                CardMultipleChoice card = new CardMultipleChoice(id, deckId, question, answerOptions, correctOption);
                cards.add(card);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving cards: " + e);
        }

        return cards;
    }
}
