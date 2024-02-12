package com.example.texashold.service;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * enum CardKind
 */

public enum CardKind {
    SPADES("S"),
    HEARTS("H"),
    DIAMONDS("D"),
    CLUBS("C");

    public  String kind;

    CardKind(String kind) {
        this.kind = kind;
    }
}
