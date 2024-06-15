package com.mogorovskiy.dao;

public interface CardDao<T> {
    void createCard(T card);
}
