package com.example.texashold.service;

import com.example.texashold.exception.WrongArgException;
import lombok.Getter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PokerHand
 *
 * Старшинство комбинаций по возрастанию
 * Кикер. Старшая карта.
 * Пара. Две карты одного достоинства.
 * Две пары. Две карты одного достоинства, две карты другого достоинства.
 * Сет. Три карты одного достоинства.
 * Стрит. Пять карт, которые выстроились по старшинству.
 * Флеш. Пять карт одной масти.
 * Фулл-хаус. Сет плюс пара.
 * Каре. Четыре карты одного достоинства.
 * Стрит-флеш. Пять карт одной масти, которые выстроились по старшинству.
 * Флеш-рояль. Пять карт от 10 до туза одной масти.
 * */

@Getter
public class PokerHand  implements Comparable<PokerHand>  {

    private final List<Card> cards;
    private HandValue status;
    private int pokerHandValue = 0;
    private final Map<Integer,List<Card>> mapCardValues;
    private final Map<CardKind,List<Card>> mapCardKind;
    public PokerHand(String s) throws Exception {
        String [] cardsStr = s.trim().split(" ");
        List<String> listCardsStr = new ArrayList<>(Arrays.asList(cardsStr));

        listCardsStr.removeAll(Arrays.asList("", null));
        if(listCardsStr.size()!=5){
            throw new WrongArgException("неверное количество аргументов");
        }
        cards = listCardsStr.stream().map(Card::new).sorted().collect(Collectors.toList());
        mapCardValues = cards.stream().collect(Collectors.groupingBy(Card::getValue));
        mapCardKind = cards.stream().collect(Collectors.groupingBy(Card::getKind));
        setStatus();
        setPokerHandValue();
    }

    private void setPokerHandValue(){
        pokerHandValue = pokerHandValue + status.getValue() + cards.get(cards.size()-1).getValue();
    }


    private void setStatus() throws Exception {
        if(isStraight()){
            if(mapCardKind.size()==1){
                if(cards.get(0).getValue()==cards.get(0).getListAcceptableValuesCard().indexOf("T")){
                    this.status = HandValue.FLUSHROAL;
                }else{
                    this.status = HandValue.STRAIGHTFLUSH;
                }
            }else{
                this.status = HandValue.STRAIGHT;
            }
        }else{
            if(mapCardKind.size()==1){
                this.status = HandValue.FLUSH;
            }else{
                if(mapCardValues.values().stream().filter(list->list.size()==4).count()==1){
                    this.status = HandValue.FOURKINDS;
                }else{
                    if(mapCardValues.values().stream().filter(list->list.size()==3).count()==1){
                       if(mapCardValues.values().stream().filter(list->list.size()==2).count()==1){
                           this.status = HandValue.FULLHOUSE;
                       }else{
                           this.status = HandValue.THREEKINDS;
                       }
                    }else{
                       var count = mapCardValues.values().stream().filter(list->list.size()==2).count();
                        switch ((int) count) {
                            case 0 -> this.status = HandValue.HIGHCARD;
                            case 1 -> this.status = HandValue.PAIR;
                            case 2 -> this.status = HandValue.TWOPAIRS;
                            default -> throw new Exception();
                        }
                    }
                }
            }
        }
    }

    public boolean isStraight(){
        int iValue =cards.get(0).getValue();
        for(int i = 0; i< cards.size();i++){
            if(cards.get(i).getValue()!=iValue){
                return false;
            }
            iValue++;
        }
        return true;
    }

    @Override
    public int compareTo(PokerHand another) {
       return  this.getPokerHandValue() - another.getPokerHandValue();
    }
}
