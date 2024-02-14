package com.example.texashold.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class ListPokerHands for work with list PokerHand
 * */

@Getter
@NoArgsConstructor
public class ListPokerHands {
    private final List<PokerHand> listPokerHands = new ArrayList<>();
    public List<PokerHand> getSortedList(){
        return listPokerHands
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
