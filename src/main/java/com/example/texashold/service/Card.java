package com.example.texashold.service;

import com.example.texashold.exception.WrongArgException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class Card
 */
@AllArgsConstructor
@Getter
public class Card implements Comparable<Card>{
    private List<String> listAcceptableValuesCard= Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
    private List<String> listAcceptableCardKind=Arrays.asList("S", "H", "D","C");
    private int value;
    private CardKind kind;

    public Card(String str){
        var arr = str.split("");

        if(arr.length != 2||!listAcceptableValuesCard.contains(arr[0])||!listAcceptableCardKind.contains(arr[1])){
            throw new WrongArgException("неверное значение аргумента");
        }
        kind = CardKind.getCardKindByString(arr[1]);
        value =listAcceptableValuesCard.indexOf(arr[0]);
    }

    @Override
    public int compareTo(Card another) {
        return  this.value - another.getValue();
    }
}
