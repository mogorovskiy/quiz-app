package com.mogorovskiy.quiz.executor.impl;

import com.mogorovskiy.model.card.Card;
import com.mogorovskiy.model.card.CardMultipleChoice;
import com.mogorovskiy.quiz.executor.QuizExecutor;

import java.util.List;
import java.util.Scanner;

public class PlayMultipleChoice implements QuizExecutor {
    @Override
    public void play(Scanner scanner, List<Card> cards) {
        for (Card card : cards) {
            if (card instanceof CardMultipleChoice) {
                CardMultipleChoice mcCard = (CardMultipleChoice) card;
                System.out.println("\n" + mcCard.getQuestion());
                String[] options = mcCard.getAnswerOptions();
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }

                int userAnswer = scanner.nextInt();
                scanner.nextLine();

                if (userAnswer == mcCard.getCorrectOption() + 1) {
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect. Correct answer is: " + options[mcCard.getCorrectOption()]);
                }
            }
        }
    }
}
