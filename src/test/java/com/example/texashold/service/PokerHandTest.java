package com.example.texashold.service;

import com.example.texashold.exception.WrongArgException;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PokerHandTest  for testing class PokerHand
 * */

class PokerHandTest {
    @Test
    void checkCreatePokerHandWithExcessArgs() throws Exception {
        WrongArgException exception = assertThrows(WrongArgException.class, () -> {
            new  PokerHand(" KS 2H 5C JD TD TD ");
        });
        assertTrue(exception.getMessage().contains("неверное количество аргументов"));
    }

    @Test
    void checkCreatePokerHandWithLackArgs() throws Exception {
        WrongArgException exception = assertThrows(WrongArgException.class, () -> {
            new  PokerHand(" KS 2H 5C JD");
        });
        assertTrue(exception.getMessage().contains("неверное количество аргументов"));
    }

    @Test
    void checkCreatePokerHandWithWrongArgs() throws Exception {
        WrongArgException exception1 = assertThrows(WrongArgException.class, () -> {
            new  PokerHand(" KS1 2H 5C JD TD");
        });
        assertTrue(exception1.getMessage().contains("неверное значение аргумента"));

        WrongArgException exception2 = assertThrows(WrongArgException.class, () -> {
            new  PokerHand(" KS 2H3 5C JD TD ");
        });
        assertTrue(exception2.getMessage().contains("неверное значение аргумента"));

        WrongArgException exception3 = assertThrows(WrongArgException.class, () -> {
            new  PokerHand(" KS 2H 5C4 JD TD");
        });
        assertTrue(exception3.getMessage().contains("неверное значение аргумента"));

        WrongArgException exception4 = assertThrows(WrongArgException.class, () -> {
            new  PokerHand(" KS 2H 5C JDR TD");
        });
        assertTrue(exception4.getMessage().contains("неверное значение аргумента"));

        WrongArgException exception5 = assertThrows(WrongArgException.class, () -> {
            new  PokerHand(" KS 2H 5C JD TDz ");
        });
        assertTrue(exception5.getMessage().contains("неверное значение аргумента"));
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

    @Test
    void checkHighCard() throws Exception {
        PokerHand hand1 = new  PokerHand("   KS   KH   5C    2D   2H  ");
        assertFalse(hand1.getStatus().equals(HandValue.HIGHCARD));
        assertEquals(hand1.getPokerHandValue(), 300);

        PokerHand hand2 = new  PokerHand("   KS   KH   5C    2D   2H  ");
        assertFalse(hand2.getStatus().equals(HandValue.HIGHCARD));
        assertEquals(hand2.getPokerHandValue(), 300);

        PokerHand hand3 = new  PokerHand("   3S   KH   5C    2D   4H  ");
        assertTrue(hand3.getStatus().equals(HandValue.HIGHCARD));
        assertEquals(hand3.getPokerHandValue(), 11);
        hand2.getPokerHandValue();
    }

    @Test
    void checkPair() throws Exception {
        PokerHand hand1 = new  PokerHand("   KS   2H   5C    JD   TH  ");
        assertFalse(hand1.getStatus().equals(HandValue.PAIR));
        assertEquals(hand1.getPokerHandValue(), 11);

        PokerHand hand2 = new  PokerHand("   2S   2H   2C    3D   TH  ");
        assertFalse(hand2.getStatus().equals(HandValue.PAIR));
        assertEquals(hand2.getPokerHandValue(), 400);

        PokerHand hand3 = new  PokerHand("   KS   3H   5C    2D   2H  ");
        assertTrue(hand3.getStatus().equals(HandValue.PAIR));
        assertEquals(hand3.getPokerHandValue(), 200);
    }

    @Test
    void checkTwoPairs() {
        PokerHand hand1 = new  PokerHand("   KS   2H   5C    JD   TH  ");
        assertFalse(hand1.getStatus().equals(HandValue.TWOPAIRS));
        assertEquals(hand1.getPokerHandValue(), 11);

        PokerHand hand2 = new  PokerHand("   KS   2H   2C    3D   TH  ");
        assertFalse(hand2.getStatus().equals(HandValue.TWOPAIRS));
        assertEquals(hand2.getPokerHandValue(), 200);

        PokerHand hand3 = new  PokerHand("   KS   KH   5C    2D   2H  ");
        assertTrue(hand3.getStatus().equals(HandValue.TWOPAIRS));
        assertEquals(hand3.getPokerHandValue(), 300);
    }

    @Test
    void checkThreeKind() {
        PokerHand hand1 = new  PokerHand("   KS   2H   5C    JD   TH  ");
        assertFalse(hand1.getStatus().equals(HandValue.THREEKINDS));
        assertEquals(hand1.getPokerHandValue(), 11);

        PokerHand hand2 = new  PokerHand("   KS   2H   2C    3D   TH  ");
        assertFalse(hand2.getStatus().equals(HandValue.THREEKINDS));
        assertEquals(hand2.getPokerHandValue(), 200);

        PokerHand hand3 = new PokerHand("   KS   KH   5C    KD   2H  ");
        assertTrue(hand3.getStatus().equals(HandValue.THREEKINDS));
        assertEquals(hand3.getPokerHandValue(), 400);
    }

    @Test
    void checkStraight() {
        PokerHand hand1 = new  PokerHand("   KS   2H   5C    JD   TH  ");
        assertFalse(hand1.getStatus().equals(HandValue.STRAIGHT));
        assertEquals(hand1.getPokerHandValue(), 11);

        PokerHand hand2 = new  PokerHand("   KS   2H   2C   2D   KH  ");
        assertFalse(hand2.getStatus().equals(HandValue.STRAIGHT));
        assertEquals(hand2.getPokerHandValue(), 700);

        PokerHand hand3 = new  PokerHand("  8S   4H   6C    5D  7H  ");
        assertTrue(hand3.getStatus().equals(HandValue.STRAIGHT));
        assertEquals(hand3.getPokerHandValue(), 500);
    }

    @Test
    void checkFlush() {
        PokerHand hand1 = new  PokerHand("   KS   2H   5C    JD   TH  ");
        assertFalse(hand1.getStatus().equals(HandValue.FLUSH));
        assertEquals(hand1.getPokerHandValue(), 11);

        PokerHand hand2 = new  PokerHand("   KS   2H   2C   2D   KH  ");
        assertFalse(hand2.getStatus().equals(HandValue.FLUSH));
        assertEquals(hand2.getPokerHandValue(), 700);

        PokerHand hand3 = new  PokerHand("  KS   2S   3S    5S  TS  ");
        assertTrue(hand3.getStatus().equals(HandValue.FLUSH));
        assertEquals(hand3.getPokerHandValue(), 600);
    }

    @Test
    void checkFullHouse() {
        PokerHand hand1 = new  PokerHand("   KS   2H   5C    JD   TH  ");
        assertFalse(hand1.getStatus().equals(HandValue.FULLHOUSE));
        assertEquals(hand1.getPokerHandValue(), 11);

        PokerHand hand2 = new  PokerHand("   KS   2H   2C   2D   KH  ");
        assertTrue(hand2.getStatus().equals(HandValue.FULLHOUSE));
        assertEquals(hand2.getPokerHandValue(), 700);

        PokerHand hand3 = new  PokerHand("  KS   2S   2S    2S  TS  ");
        assertFalse(hand3.getStatus().equals(HandValue.FULLHOUSE));
        assertEquals(hand3.getPokerHandValue(), 600);
    }

    @Test
    void checkFourKind() {
        PokerHand hand1 = new  PokerHand("   KS   2H   5C    JD   TH  ");
        assertFalse(hand1.getStatus().equals(HandValue.FOURKINDS));
        assertEquals(hand1.getPokerHandValue(), 11);

        PokerHand hand2 = new  PokerHand("   KS   2H   2C    2D   TH  ");
        assertFalse(hand2.getStatus().equals(HandValue.FOURKINDS));
        assertEquals(hand2.getPokerHandValue(), 400);

        PokerHand hand3 = new  PokerHand("   KS   KH   5C    KD   KH  ");
        assertTrue(hand3.getStatus().equals(HandValue.FOURKINDS));
        assertEquals(hand3.getPokerHandValue(), 800);
    }

    @Test
    void checkStraightFlush() {
        PokerHand hand1 = new  PokerHand("   KS   2H   5C    JD   TH  ");
        assertFalse(hand1.getStatus().equals(HandValue.STRAIGHTFLUSH));
        assertEquals(hand1.getPokerHandValue(), 11);

        PokerHand hand2 = new  PokerHand("   3S   2S   4S    6S  5S  ");
        assertTrue(hand2.getStatus().equals(HandValue.STRAIGHTFLUSH));
        assertEquals(hand2.getPokerHandValue(), 900);

        PokerHand hand3 = new  PokerHand("  KS   2S   2S    2S  TS  ");
        assertFalse(hand3.getStatus().equals(HandValue.STRAIGHTFLUSH));
        assertEquals(hand3.getPokerHandValue(), 600);
    }

    @Test
    void checkFlushRoal() {
        PokerHand hand1 = new  PokerHand("   KS   2H   5C    JD   TH  ");
        assertFalse(hand1.getStatus().equals(HandValue.FLUSHROAL));
        assertEquals(hand1.getPokerHandValue(), 11);

        PokerHand hand2 = new  PokerHand("   KS   2S   5S    JS  TS  ");
        assertFalse(hand2.getStatus().equals(HandValue.FLUSHROAL));
        assertEquals(hand2.getPokerHandValue(), 600);

        PokerHand hand3 = new  PokerHand("   KS   QS   AS    JS  TS  ");
        assertTrue(hand3.getStatus().equals(HandValue.FLUSHROAL));
        assertEquals(hand3.getPokerHandValue(), 1000);
    }
}
