package com.mogorovskiy.quiz.executor.impl;

import com.mogorovskiy.model.card.CardMultipleChoice;
import com.mogorovskiy.quiz.executor.QuizExecutor;

import java.util.List;
import java.util.Scanner;

public class QuizExecutorMultipleChoice implements QuizExecutor<CardMultipleChoice> {

    @Override
    public void execute(Scanner scanner, List<CardMultipleChoice> cards) {
        int score = 0;

        for (CardMultipleChoice card : cards) {
            System.out.println(card.getQuestion());
            String[] options = card.getAnswerOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            int userAnswer = scanner.nextInt();
            scanner.nextLine();

            if (userAnswer == card.getCorrectOption() + 1) {
                score++;
                System.out.println("Correct!");
                System.out.println("------------------------------------------");
            } else {
                System.out.println("Incorrect. Correct answer is: " + options[card.getCorrectOption() - 1]);
                System.out.println("------------------------------------------");
            }
        }

        System.out.println("Your final score is: " + score);
    }
}
