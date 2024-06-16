package com.mogorovskiy.quiz.service.impl;

import com.mogorovskiy.dao.DeckDao;
import com.mogorovskiy.model.Deck;
import com.mogorovskiy.quiz.service.DeckPicker;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DeckPickerImpl implements DeckPicker {

    private final DeckDao deckDao;
    private final Scanner scanner;

    public DeckPickerImpl(DeckDao deckDao) {
        this.deckDao = deckDao;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Optional<Deck> pickDeck() {
        printAllDecks();

        Long deckId = scanner.nextLong();
        scanner.nextLine();

        return deckDao.getDeckById(deckId);
    }

    private void printAllDecks() {
        List<Deck> decks = deckDao.getAllDecks();
        for (Deck deck : decks) {
            System.out.println(deck.getId() + ". " + deck.getName());
        }
    }
}
