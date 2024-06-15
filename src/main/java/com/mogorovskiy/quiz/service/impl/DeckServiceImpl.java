package com.mogorovskiy.quiz.service.impl;

import com.mogorovskiy.dao.DeckDao;

import java.util.Scanner;

public class DeckServiceImpl {

    private final DeckDao deckDao;
    private final Scanner scanner;

    public DeckServiceImpl(DeckDao deckDao) {
        this.deckDao = deckDao;
        this.scanner = new Scanner(System.in);
    }

    public void createDeck() {
        System.out.println("Enter deck name:");
        String name = scanner.nextLine();

        deckDao.createDeck(name);
        System.out.println("Deck '" + name + "' created.");
    }
}
