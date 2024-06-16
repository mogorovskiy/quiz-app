package com.mogorovskiy.model.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CardTranslation {

    private Long id;
    private final Long deckId;

    private final String question;
    private final String correctAnswer;
}
