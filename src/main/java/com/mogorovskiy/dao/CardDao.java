package com.mogorovskiy.dao;

import java.util.List;

public interface CardDao<T> {
    void createCard(T card);

    List<T> getCardsByDeckId(Long deckId);

}
