package com.mogorovskiy.model.card;
import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
public class CardTranslation {

    private final Long id;
    private final Long deckId;

    private String question;
    private String correctAnswer;
}
