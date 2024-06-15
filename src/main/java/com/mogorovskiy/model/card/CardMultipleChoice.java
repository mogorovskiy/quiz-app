package com.mogorovskiy.model.card;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CardMultipleChoice {

    private final Long id;
    private final Long deckId;

    private final String question;
    private final String[] answerOptions;
    private final int correctOption;
}
