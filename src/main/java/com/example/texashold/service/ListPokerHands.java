package com.example.texashold.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Comparator;
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
    private List<PokerHand> listPokerHands = new ArrayList<>();
    public List<PokerHand> getSortedList(){
        return listPokerHands
                .stream()
                .sorted(Comparator.comparingInt(PokerHand::getPokerHandValue))
                .collect(Collectors.toList());
    }
}
