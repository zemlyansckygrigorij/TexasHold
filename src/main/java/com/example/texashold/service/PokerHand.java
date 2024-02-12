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
public class PokerHand {
    private List listAcceptableValuesCard=Arrays.asList(new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"});
    private List listAcceptableCardKind=Arrays.asList(new String[]{"S", "H", "D","C"});

    private List<Card> cards;
    HandValue status;
    private int PokerHandValue = 0;
    private Map<Integer,List<Card>> mapCardValues;
    public PokerHand(String s) throws WrongArgException {
        String [] cardsStr = s.trim().split(" ");
        List<String> listCardsStr = new ArrayList<>(Arrays.asList(cardsStr));

        listCardsStr.removeAll(Arrays.asList("", null));

        // check amount arguments
        if(listCardsStr.size()!=5){
            throw new WrongArgException("неверное количество аргументов");
        }
        // check value arguments
        if(listCardsStr.stream().filter(c ->{
            var arr = c.split("");
            return arr.length != 2||!listAcceptableValuesCard.contains(arr[0])||!listAcceptableCardKind.contains(arr[1]);
        }).count()>0) throw new WrongArgException("неверное значение аргумента");

        cards = listCardsStr.stream().map(cardS ->{
            var arr = cardS.split("");

            CardKind cardKind =  Arrays.stream(CardKind.values()).filter(cKind -> cKind.kind.equalsIgnoreCase(arr[1]))
                    .findFirst()
                    .get();
            int index =listAcceptableValuesCard.indexOf(arr[0]);
            Card card = new Card(index,cardKind);
            return card;
        }).sorted(Comparator.comparing(с -> с.getValue())).collect(Collectors.toList());
        mapCardValues = cards.stream().collect(Collectors.groupingBy(Card::getValue));

        if(checkFlushRoal()){
           status=HandValue.FLUSHROAL;
           PokerHandValue = status.getValue();
           return;
        }

        if(checkFourKind()){
            status=HandValue.FOURKINDS;
            PokerHandValue = status.getValue();
            return;
        }

        if(checkStraightFlush()){
            status=HandValue.STRAIGHTFLUSH;
            PokerHandValue = status.getValue();
            return;
        }

        if(checkFullHouse()){
            status=HandValue.FULLHOUSE;
            PokerHandValue = status.getValue();
            return;
        }

        if(checkFlush()){
            status=HandValue.FLUSH;
            PokerHandValue = status.getValue();
            return;
        }

        if(checkStraight()){
            status=HandValue.STRAIGHT;
            PokerHandValue = status.getValue();
            return;
        }

        if(checkThreeKind()){
            status=HandValue.THREEKINDS;
            PokerHandValue = status.getValue();
            return;
        }

        if(checkTwoPairs()){
            status=HandValue.TWOPAIRS;
            PokerHandValue = status.getValue();
            return;
        }
        if(checkPair()){
            status = HandValue.PAIR;
            PokerHandValue = status.getValue();
            return;
        }

        status =HandValue.HIGHCARD;
        PokerHandValue = cards.stream().max(Comparator.comparing(Card::getValue)).get().getValue();
    }

    public boolean checkPair(){
        if(mapCardValues.values().stream().filter(list->list.size()==2).count()==1&&
                mapCardValues.values().size()==4){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkTwoPairs(){
        if(mapCardValues.values().stream().filter(list->list.size()==2).count()==2){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkThreeKind(){
        if(mapCardValues.values().stream().filter(list->list.size()==3).count()==1&&mapCardValues.values().size()==3) {
            return true;
        }
        return false;
    }

    public boolean checkStraight(){
        if(cards.stream().collect(Collectors.groupingBy(Card::getKind)).size()==1){
            return false;
        }
        int iValue =cards.get(0).getValue();

        for(int i = 0; i< cards.size();i++){
            if(cards.get(i).getValue()!=iValue){
                return false;
            }
            iValue++;
        }
        return true;
    }

    public boolean checkFlush(){
        if(checkStraightFlush()){
            return false;
        }
        if(cards.stream().collect(Collectors.groupingBy(Card::getKind)).size()==1) {
            return true;
        }
        return false;
    }

    public boolean checkFullHouse(){
        if(mapCardValues.values().stream().filter(list->list.size()==3).count()==1&&
                mapCardValues.values().stream().filter(list->list.size()==2).count()==1){
            return true;
        }
        return false;
    }

    public boolean checkFourKind(){
        if(mapCardValues.values().stream().filter(list->list.size()==4).count()==1){
            return true;
        }
        return false;
    }

    public boolean checkStraightFlush(){
        if(checkFlushRoal()){
            return false;
        }
        if(cards.stream().collect(Collectors.groupingBy(Card::getKind)).size()!=1){
            return false;
        }
        var value =cards.get(0).getValue();
        for(int i =0;i<5;i++){
            if(cards.get(i).getValue()!=value){
                return false;
            }
            value++;
        }
        return true;
    }

    public boolean checkFlushRoal(){
        if(cards.stream().collect(Collectors.groupingBy(Card::getKind)).size()!=1){
            return false;
        }
        var value =8;
        for(int i =0;i<5;i++){
            if(cards.get(i).getValue()!=value){
                return false;
            }
            value++;
        }
        return true;
    }
}
