package com.mogorovskiy.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Deck{

    private Long id;
    private String name;
}
