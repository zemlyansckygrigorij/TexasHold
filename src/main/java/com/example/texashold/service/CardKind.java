package com.example.texashold.service;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * enum CardKind
 */
@Getter
public enum CardKind {
    SPADES("S"),
    HEARTS("H"),
    DIAMONDS("D"),
    CLUBS("C");

    private final String kind;

    CardKind(String kind) {
        this.kind = kind;
    }

    public static CardKind getCardKindByString(String str){
       return Arrays.stream(CardKind.values()).filter(cKind -> cKind.getKind().equalsIgnoreCase(str))
                .findFirst()
                .get();
    }
}
