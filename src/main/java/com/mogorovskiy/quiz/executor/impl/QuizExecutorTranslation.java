package com.mogorovskiy.quiz.executor.impl;

import com.mogorovskiy.model.card.CardTranslation;
import com.mogorovskiy.quiz.executor.QuizExecutor;

import java.util.List;
import java.util.Scanner;

public class QuizExecutorTranslation implements QuizExecutor<CardTranslation> {

    @Override
    public void execute(Scanner scanner, List<CardTranslation> cards) {
        int score = 0;

        for (CardTranslation card : cards) {
            System.out.println(card.getQuestion());

            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase(card.getCorrectAnswer() )) {
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
