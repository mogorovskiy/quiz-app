package com.mogorovskiy.quiz.service;

import com.mogorovskiy.model.Deck;

import java.util.Optional;

public interface DeckPicker {
    Optional<Deck> pickDeck();
}
