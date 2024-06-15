package com.mogorovskiy.quiz.service;

import com.mogorovskiy.model.card.Card;

import java.util.List;
import java.util.Scanner;

public interface DeckPicker {
    List<Card> chooseGameMod(Scanner scanner);
}
