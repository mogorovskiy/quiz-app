package com.mogorovskiy.quiz.service.impl;

import com.mogorovskiy.dao.DeckDao;
import com.mogorovskiy.quiz.service.DeckService;

import java.util.Scanner;

public class DeckServiceImpl implements DeckService {

    private final DeckDao deckDao;
    private final Scanner scanner;

    public DeckServiceImpl(DeckDao deckDao) {
        this.deckDao = deckDao;
        this.scanner = new Scanner(System.in);
    }

    public void createDeck() {
        System.out.println("Enter deck name:");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("Deck name cannot be empty. Please try again.");
            return;
        }

        deckDao.createDeck(name);
        System.out.println("Deck '" + name + "' created.");
    }
}
