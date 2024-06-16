package com.mogorovskiy.quiz.executor.impl;

import com.mogorovskiy.model.card.CardTranslation;
import com.mogorovskiy.quiz.executor.QuizExecutor;

import java.util.List;
import java.util.Scanner;

public class QuizExecutorTranslation implements QuizExecutor<CardTranslation> {

    @Override
    public void execute(Scanner scanner, List<CardTranslation> cards) {
        if (cards == null || cards.isEmpty()) {
            System.out.println("No cards to display.");
            return;
        }

        int score = 0;

        for (CardTranslation card : cards) {
            System.out.println(card.getQuestion());

            String userAnswer = scanner.nextLine();
            if (userAnswer.trim().equalsIgnoreCase(card.getCorrectAnswer().trim())) {
                score++;
                System.out.println("Correct!");
                System.out.println("-----------------------------------------------");
            } else {
                System.out.println("Incorrect. Correct answer is: " + card.getCorrectAnswer());
                System.out.println("-----------------------------------------------");
            }
        }

        System.out.println("Your final score is: " + score);
    }
}
