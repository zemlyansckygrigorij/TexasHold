package com.example.texashold.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class Card
 */
@AllArgsConstructor
@Getter
public class Card {
    private int value;
    private CardKind kind;
}
