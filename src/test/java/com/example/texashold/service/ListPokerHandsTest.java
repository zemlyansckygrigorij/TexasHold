package com.example.texashold.service;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class ListPokerHandsTest for testing class ListPokerHands
 * */
class ListPokerHandsTest {
    @Test
    void sort() {
        ListPokerHands list = new ListPokerHands();
        list.getListPokerHands().add( new PokerHand("   KS   2S   3S    5S  TS "));
        list.getListPokerHands().add( new PokerHand("   KS   2H   2C   2D   KH  "));
        list.getListPokerHands().add( new PokerHand("    KS   KH   5C    KD   KH "));
        list.getListPokerHands().add( new PokerHand("     3S   2S   4S    6S  5S "));
        list.getListPokerHands().add( new PokerHand("  KS   QS   AS    JS  TS  "));

        list.getListPokerHands().add( new PokerHand("   KS   2H   5C    JD   TH  "));
        list.getListPokerHands().add( new PokerHand("   KS   KH   5C    2D   2H   "));
        list.getListPokerHands().add( new PokerHand("   3S   KH   5C    2D   4H   "));
        list.getListPokerHands().add( new PokerHand("    KS   3H   5C    2D   2H   "));
        list.getListPokerHands().add( new PokerHand("  KS   KH   5C    2D   2H   "));
        list.getListPokerHands().add( new PokerHand("   KS   KH   5C    KD   2H   "));
        list.getListPokerHands().add( new PokerHand("    8S   4H   6C    5D  7H "));

        List<PokerHand> listPokerHands = list.getListPokerHands();
        assertEquals(listPokerHands.get(0).status,HandValue.FLUSH);
        assertEquals(listPokerHands.get(1).status,HandValue.FULLHOUSE);
        assertEquals(listPokerHands.get(2).status,HandValue.FOURKINDS);
        assertEquals(listPokerHands.get(3).status,HandValue.STRAIGHTFLUSH);
        assertEquals(listPokerHands.get(4).status,HandValue.FLUSHROAL);
        assertEquals(listPokerHands.get(5).status,HandValue.HIGHCARD);
        assertEquals(listPokerHands.get(6).status,HandValue.TWOPAIRS);
        assertEquals(listPokerHands.get(7).status,HandValue.HIGHCARD);
        assertEquals(listPokerHands.get(8).status,HandValue.PAIR);
        assertEquals(listPokerHands.get(9).status,HandValue.TWOPAIRS);
        assertEquals(listPokerHands.get(10).status,HandValue.THREEKINDS);
        assertEquals(listPokerHands.get(11).status,HandValue.STRAIGHT);

        List<PokerHand> sortedList = list.getSortedList();
        assertEquals(sortedList.get(0).status,HandValue.HIGHCARD);
        assertEquals(sortedList.get(1).status,HandValue.HIGHCARD);
        assertEquals(sortedList.get(2).status,HandValue.PAIR);
        assertEquals(sortedList.get(3).status,HandValue.TWOPAIRS);
        assertEquals(sortedList.get(4).status,HandValue.TWOPAIRS);
        assertEquals(sortedList.get(5).status,HandValue.THREEKINDS);
        assertEquals(sortedList.get(6).status,HandValue.STRAIGHT);
        assertEquals(sortedList.get(7).status,HandValue.FLUSH);
        assertEquals(sortedList.get(8).status,HandValue.FULLHOUSE);
        assertEquals(sortedList.get(9).status,HandValue.FOURKINDS);
        assertEquals(sortedList.get(10).status,HandValue.STRAIGHTFLUSH);
        assertEquals(sortedList.get(11).status,HandValue.FLUSHROAL);
    }
}