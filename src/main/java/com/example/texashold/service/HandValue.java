package com.example.texashold.service;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * enum HandValue
 */

public enum HandValue {

    HIGHCARD(100),
    PAIR(200),
    TWOPAIRS(300),
    THREEKINDS(400),
    STRAIGHT(500),
    FLUSH(600),
    FULLHOUSE(700),
    FOURKINDS(800),
    STRAIGHTFLUSH(900),
    FLUSHROAL(1000);
    private final int value;

    HandValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
