package com.example.texashold.service;

import com.example.texashold.exception.WrongArgException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PokerHandTest  for testing class PokerHand
 * */

class PokerHandTest {
    String [] countArgs = {" KS 2H 5C JD TD TD "," KS 2H 5C JD"};
    String []  wrongArgs= {" KS1 2H 5C JD TD"," KS 2H3 5C JD TD "," KS 2H 5C4 JD TD"," KS 2H 5C JDR TD"," KS 2H 5C JD TDz "};

    Map<String,HandValue> mapFalse = new HashMap<>();
    Map<String,HandValue>  mapTrue = new HashMap<>();
    public PokerHandTest(){
        mapFalse.put("   KS   KH   5C    5D   5H  ",HandValue.HIGHCARD);
        mapFalse.put("   KS   KH   5C    2D   2H  ",HandValue.HIGHCARD);
        mapFalse.put("   3S   2H   5C    JD   TH  ",HandValue.PAIR);
        mapFalse.put("   2S   2H   2C    3D   TH  ",HandValue.PAIR);
        mapFalse.put("   KS   2H   5C    JD   TH  ",HandValue.TWOPAIRS);
        mapFalse.put("   KS   2H   2C    3D   TH  ",HandValue.TWOPAIRS);
        mapFalse.put("   4S   2H   5C    JD   TH  ",HandValue.THREEKINDS);
        mapFalse.put("   4S   2H   2C    3D   TH  ",HandValue.THREEKINDS);
        mapFalse.put("   TS   2H   5C    JD   TH  ",HandValue.STRAIGHT);
        mapFalse.put("   KS   2H   2C   2D   KH  ",HandValue.STRAIGHT);
        mapFalse.put("   6S   2H   6C    JD   TH  ",HandValue.FLUSH);
        mapFalse.put("   7S   2H   2C   2D   KH  ",HandValue.FLUSH);
        mapFalse.put("   8S   2H   5C    JD   TH  ",HandValue.FULLHOUSE);
        mapFalse.put("  KS   2S   2S    2S  TS  ",HandValue.FULLHOUSE);
        mapFalse.put("   9S   2H   5C    JD   TH  ",HandValue.FOURKINDS);
        mapFalse.put("   KS   2H   2C    2D   TH  ",HandValue.FOURKINDS);
        mapFalse.put("   6S   2H   5C    JD   TH  ",HandValue.STRAIGHTFLUSH);
        mapFalse.put("  9S   2S   2S    2S  TS  ",HandValue.STRAIGHTFLUSH);
        mapFalse.put("   7S   2H   5C    JD   TH  ",HandValue.FLUSHROAL);
        mapFalse.put("   KS   2S   5S    JS  TS  ",HandValue.FLUSHROAL);

        mapTrue.put("   3S   KH   5C    2D   4H  ",HandValue.HIGHCARD);
        mapTrue.put("   KS   3H   5C    2D   2H  ",HandValue.PAIR);
        mapTrue.put("   KS   KH   5C    2D   2H  ",HandValue.TWOPAIRS);
        mapTrue.put("   KS   KH   5C    KD   2H  ",HandValue.THREEKINDS);
        mapTrue.put("  8S   4H   6C    5D  7H  ",HandValue.STRAIGHT);
        mapTrue.put("  KS   2S   3S    5S  TS  ",HandValue.FLUSH);
        mapTrue.put("   KS   2H   2C   2D   KH  ",HandValue.FULLHOUSE);
        mapTrue.put("   KS   KH   5C    KD   KH  ",HandValue.FOURKINDS);
        mapTrue.put("   3S   2S   4S    6S  5S  ",HandValue.STRAIGHTFLUSH);
        mapTrue.put("   8S   7S   4S    6S  5S  ",HandValue.STRAIGHTFLUSH);
        mapTrue.put("   KS   QS   AS    JS  TS  ",HandValue.FLUSHROAL);
    }

    @Test
    void checkStatusFalse(){
        mapFalse.forEach((key,value)-> {
            try {
                PokerHand pokerHand = new PokerHand(key);
                assertNotEquals(pokerHand.getStatus(), value);
            } catch (Exception e) {
                e.getStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
    @Test
    void checkStatusTrue(){
        mapTrue.forEach((key,value)-> {
            try {
                PokerHand pokerHand = new PokerHand(key);
                assertEquals(pokerHand.getStatus(), value);
            } catch (Exception e) {
                e.getStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void checkCreatePokerHandWithWrongCountArgs(){
        for( String count: countArgs){
            assertTrue(getException(count).getMessage().contains("неверное количество аргументов"));
        }
    }

    @Test
    void checkCreatePokerHandWithWrongArgs(){
        for( String arg: wrongArgs){
            assertTrue(getException(arg).getMessage().contains("неверное значение аргумента"));
        }
    }

    @Test
    void checkSorted() throws Exception {
        PokerHand hand = new  PokerHand("   KS   2H   5C    JD   TH  ");
        List<Card> cards = hand.getCards();
        assertEquals(cards.get(0).getValue(),0);
        assertEquals(cards.get(0).getKind(),CardKind.HEARTS);
        assertEquals(cards.get(1).getValue(),3);
        assertEquals(cards.get(1).getKind(),CardKind.CLUBS);
        assertEquals(cards.get(2).getValue(),8);
        assertEquals(cards.get(2).getKind(),CardKind.HEARTS);
        assertEquals(cards.get(3).getValue(),9);
        assertEquals(cards.get(3).getKind(),CardKind.DIAMONDS);
        assertEquals(cards.get(4).getValue(),11);
        assertEquals(cards.get(4).getKind(),CardKind.SPADES);
    }

    public WrongArgException getException(String str){
        return assertThrows(WrongArgException.class, () -> {
            new  PokerHand(str);
        });
    }
}
