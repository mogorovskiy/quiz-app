package com.mogorovskiy.quiz.service.impl;

import com.mogorovskiy.dao.DeckDao;

import java.util.Scanner;

public class DeckServiceImpl {
    private final DeckDao deckDao;

    public DeckServiceImpl(DeckDao deckDao) {
        this.deckDao = deckDao;
    }

    public void createDeck(Scanner scanner) {
        System.out.println("Enter deck name:");
        String name = scanner.nextLine();

        long deckId = deckDao.createDeck(name);
        System.out.println("Deck '" + name + "' created with ID: " + deckId);
    }
}
