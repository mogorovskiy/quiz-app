package com.mogorovskiy.model.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CardMultipleChoice {

    private Long id;
    private final Long deckId;

    private final String question;
    private final String[] answerOptions;
    private final int correctOption;
}
