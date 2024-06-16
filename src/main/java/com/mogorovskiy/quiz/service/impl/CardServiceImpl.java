package com.mogorovskiy.quiz.service.impl;

import com.mogorovskiy.dao.DeckDao;
import com.mogorovskiy.dao.impl.CardMultipleChoiceDaoImpl;
import com.mogorovskiy.dao.impl.CardTranslationDaoImpl;
import com.mogorovskiy.model.Deck;
import com.mogorovskiy.model.card.CardMultipleChoice;
import com.mogorovskiy.model.card.CardTranslation;
import com.mogorovskiy.quiz.service.CardService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private static final int MIN_OPTIONS = 1;
    private static final int MAX_OPTIONS = 4;

    private final DeckDao deckDao;
    private final Scanner scanner;

    public void addCardsToDeck() {
        System.out.println("Choose deck ID to add cards:");
        List<Deck> decks = deckDao.getAllDecks();
        for (Deck deck : decks) {
            System.out.println(deck.getId() + ". " + deck.getName());
        }

        long deckId = scanner.nextLong();
        scanner.nextLine();

        while (true) {
            System.out.println("\nChoose card type to add:");
            System.out.println("1. Multiple Choice");
            System.out.println("2. Translation");
            System.out.println("3. Exit to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMultipleChoiceCard(scanner, deckId);
                case 2 -> addTranslationCard(scanner, deckId);
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private void addMultipleChoiceCard(Scanner scanner, Long deckId) {
        System.out.println("Enter question:");
        String question = scanner.nextLine();

        System.out.println("Enter options (comma-separated):");
        String optionsInput = scanner.nextLine();
        String[] options = optionsInput.split(",");

        if (optionsAreInvalid(options)) {
            System.out.println("Invalid options. Please enter between " + MIN_OPTIONS + " and " + MAX_OPTIONS + " options.");
            return;
        }

        System.out.println("Enter index of correct option (1-" + options.length + "):");
        int correctOption = scanner.nextInt();

        if (correctOptionIsInvalid(correctOption, options.length)) {
            System.out.println("Invalid correct option index " + correctOption + " (max allowed is " + (options.length - 1) + ")");
            return;
        }

        scanner.nextLine();

        CardMultipleChoice card = new CardMultipleChoice(deckId, question, options, correctOption);

        CardMultipleChoiceDaoImpl cardMultipleChoiceDao = new CardMultipleChoiceDaoImpl();
        cardMultipleChoiceDao.createCard(card);

        System.out.println("Multiple Choice Card added successfully to deck Id " + deckId);
    }

    private boolean correctOptionIsInvalid(int correctOption, int length) {
        return correctOption < 0 || correctOption >= length;
    }

    private boolean optionsAreInvalid(String[] options) {
        return options.length < MIN_OPTIONS || options.length > MAX_OPTIONS;
    }

    private void addTranslationCard(Scanner scanner, long deckId) {
        System.out.println("Enter question:");
        String question = scanner.nextLine();
        if (question.trim().isEmpty()) {
            System.out.println("Question cannot be empty. Please try again.");
            return;
        }

        System.out.println("Enter the correct answer:");
        String correctAnswer = scanner.nextLine();
        if (correctAnswer.trim().isEmpty()) {
            System.out.println("Correct answer cannot be empty. Please try again.");
            return;
        }

        CardTranslation card = new CardTranslation(deckId, question, correctAnswer);

        CardTranslationDaoImpl cardTranslationDao = new CardTranslationDaoImpl();
        cardTranslationDao.createCard(card);

        System.out.println("Translation Card added successfully to deck ID " + deckId);
    }
}
