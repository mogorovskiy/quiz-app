package com.mogorovskiy.quiz.service.impl;

import com.mogorovskiy.dao.CardMultipleChoiceDao;
import com.mogorovskiy.dao.DeckDao;
import com.mogorovskiy.model.Deck;
import com.mogorovskiy.model.card.CardMultipleChoice;
import com.mogorovskiy.quiz.service.CardService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final DeckDao deckDao;
    private final CardMultipleChoiceDao cardDao;
    private final Scanner scanner;

    public void addCardsToDeck() {
        System.out.println("Choose deck ID to add cards:");
        List<Deck> decks = deckDao.getAllDecks();
        for (Deck deck : decks) {
            System.out.println(deck.getId() + ". " + deck.getName());
        }

        int deckId = scanner.nextInt();
        scanner.nextLine();

        Deck deck = deckDao.getDeckById(deckId);

        while (true) {
            System.out.println("\nChoose card type to add:");
            System.out.println("1. Multiple Choice");
            System.out.println("2. True/False");
            System.out.println("3. Exit to main menu");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMultipleChoiceCard(scanner, deckId);
                //case 2 -> addTrueFalseCard(scanner, deckId);
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private void addMultipleChoiceCard(Scanner scanner, long deckId) {
        System.out.println("Enter question:");
        String question = scanner.nextLine();

        System.out.println("Enter options (comma-separated):");
        String optionsInput = scanner.nextLine();
        String[] options = optionsInput.split(",");

        System.out.println("Enter index of correct option:");
        int correctOption = scanner.nextInt();
        scanner.nextLine();

        CardMultipleChoice card = new CardMultipleChoice(null, question, options, correctOption);
        cardDao.createCard(card);
        System.out.println("Multiple Choice Card added successfully to deck ID " + deckId);
    }
/*
    private void addTrueFalseCard(Scanner scanner, long deckId) {
        System.out.println("Enter question:");
        String question = scanner.nextLine();

        System.out.println("Is the correct answer true or false? (true/false):");
        boolean correctAnswer = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        TrueFalseCard card = new TrueFalseCard(null, question, correctAnswer);
        cardDao.insertCard(card, deckId);
        System.out.println("True/False Card added successfully to deck ID " + deckId);
    }*/
}
