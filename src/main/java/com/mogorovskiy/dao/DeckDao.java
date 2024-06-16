package com.mogorovskiy.dao;

import com.mogorovskiy.model.Deck;

import java.util.List;
import java.util.Optional;

public interface DeckDao {

    void createDeck(String name);

    List<Deck> getAllDecks();

    Optional<Deck> getDeckById(Long deckId);
}
