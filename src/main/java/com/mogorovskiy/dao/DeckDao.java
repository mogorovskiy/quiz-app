package com.mogorovskiy.dao;

import com.mogorovskiy.model.Deck;

import java.util.List;

public interface DeckDao {

    void createDeck(String name);

    List<Deck> getAllDecks();

    List<Deck> getDecksByName(String name);
}
