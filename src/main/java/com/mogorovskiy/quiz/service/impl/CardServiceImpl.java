package com.mogorovskiy.quiz.service.impl;

import com.mogorovskiy.dao.CardDao;
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

    private final DeckDao deckDao;
    private final CardDao cardDao;
    private final Scanner scanner;

    public void addCardsToDeck() {
        System.out.println("Choose deck ID to add cards:");
        List<Deck> decks = deckDao.getAllDecks();
        for (Deck deck : decks) {
            System.out.println(deck.getId() + ". " + deck.getName());
        }

        Long deckId = scanner.nextLong();
        scanner.nextLine();

        Deck deck = deckDao.getDeckById(deckId);

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

        System.out.println("Enter index of correct option (1-4):");
        int correctOption = scanner.nextInt();
        scanner.nextLine();

        CardMultipleChoice card = new CardMultipleChoice(null, deckId, question, options, correctOption);

        CardMultipleChoiceDaoImpl cardMultipleChoiceDao = new CardMultipleChoiceDaoImpl();
        cardMultipleChoiceDao.createCard(card);

        System.out.println("Multiple Choice Card added successfully to deck Id " + deckId);
    }

    private void addTranslationCard(Scanner scanner, long deckId) {
        System.out.println("Enter question:");
        String question = scanner.nextLine();

        System.out.println("Enter the correct answer:");       //TODO!!!! indexOutOfBo..
        String correctAnswer = scanner.nextLine();
        scanner.nextLine();

        CardTranslation card = new CardTranslation(null, deckId, question, correctAnswer);

        CardTranslationDaoImpl cardTranslationDao = new CardTranslationDaoImpl();
        cardTranslationDao.createCard(card);

        System.out.println("Translation Card added successfully to deck ID " + deckId);
    }
}
