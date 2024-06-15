package com.mogorovskiy.quiz.service.impl;

import com.mogorovskiy.dao.DeckDao;
import com.mogorovskiy.model.Deck;
import com.mogorovskiy.model.card.Card;
import com.mogorovskiy.quiz.service.DeckPicker;

import java.util.List;
import java.util.Scanner;

public class DeckPickerImpl implements DeckPicker {

    private final DeckDao deckDao;

    public DeckPickerImpl(DeckDao deckDao) {
        this.deckDao = deckDao;
    }

    @Override
    public List<Card> chooseGameMod(Scanner scanner) {
        System.out.println("Choose category:");
        System.out.println("1. English");
        System.out.println("2. Java");

        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        String category = "";
        switch (categoryChoice) {
            case 1 -> category = "English";
            case 2 -> category = "Java";
            default -> {
                System.out.println("Invalid choice.");
            }
        }

        List<Deck> decks = deckDao.getDecksByName(category);
        if (decks.isEmpty()) {
            System.out.println("No decks found for category " + category);
            //return null;
        }

        System.out.println("Choose deck ID to start:");
        for (Deck deck : decks) {
            System.out.println(deck.getId() + ". " + deck.getName());
        }

        long deckId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Starting test for deck '" + category + "'...");

        return findCards(deckId);
    }

    public List<Card> findCards(Long deckId) {
        List<Card> cards = deckDao.getCardsByDeckId(deckId);
        if (cards.isEmpty()) {
            System.out.println("No cards found in deck ID " + deckId);
            //return null;
        }
        return cards;
    }
}
