package aoc2023;

import java.util.*;

public class Day07 extends AOCUtils {
    public Day07() { super("7"); }

    public enum CARD {
        A, K, Q, J, T, _9, _8, _7, _6, _5, _4, _3, _2;
    }//CARD

    public enum CARD_J {
        A, K, Q, T, _9, _8, _7, _6, _5, _4, _3, _2, J;
    }//CARD_J

    @Override
    void solve(List<String> input) {
        input.sort(Day07::compareHand);
        solution(calculatePoints(input));

        input.sort(Day07::compareHandJ);
        solution(calculatePoints(input));

    }//solve

    private static int calculatePoints(List<String> input) {
        int i = input.size();
        int ris = 0;
        for(String row : input)
            ris += (Integer.parseInt(row.split("\\s+")[1]) * i--);
        return ris;
    }//calculatePoints

    private static int getPunti(String cards) {
        return fiveOfKind(cards);
    }//getPunti

    private static <T extends Enum<T>> int getPuntiJ(String cards) {
        if(!cards.contains("J"))
            return getPunti(cards);
        else {
            int betterPoints = fiveOfKind(cards);
            for (Enum<T> val : ((Class<T>) CARD_J.class).getEnumConstants()) {
                int points = fiveOfKind(cards.replace('J', val.name().charAt(val.name().length() == 1 ? 0 : 1)));
                if (points < betterPoints)
                    betterPoints = points;
            }//for
            return betterPoints;
        }//else
    }//getPuntiJ

    private static int fiveOfKind(String cards) {
        return fillMap(cards).containsValue(5) ? 0 : fourOfKind(cards);
    }//fiveOfKind

    private static int fourOfKind(String cards) {
        return fillMap(cards).containsValue(4) ? 1 : fullHouse(cards);
    }//fourOfKink

    private static int fullHouse(String cards) {
        Map<Character, Integer> map = fillMap(cards);
        return map.containsValue(3) && map.containsValue(2) ? 2 : threeOfKind(cards);
    }//fullHouse

    private static int threeOfKind(String cards) {
        Map<Character, Integer> map = fillMap(cards);
        return map.containsValue(3) && map.values().size() == 3 ? 3 : twoPair(cards);
    }//threeOfKink

    private static int twoPair(String cards) {
        Map<Character, Integer> map = fillMap(cards);
        return map.containsValue(2) && map.containsValue(1) && map.keySet().size() == 3 ? 4 : onePair(cards);
    }//twoPair

    private static int onePair(String cards) {
        Map<Character, Integer> map = fillMap(cards);
        return map.containsValue(2) ? 5 : highCard();
    }//onePair

    private static int highCard() {
        return 6;
    }//highCard

    private static Map<Character, Integer> fillMap(String cards) {
        Map<Character, Integer> count = new HashMap<>();
        for(Character c : cards.toCharArray())
            if(!count.containsKey(c))
                count.put(c, 1);
            else
                count.put(c, count.get(c) + 1);
        return count;
    }//fillMap

    private static int compareHand(String hand1, String hand2) {

        hand1 = hand1.split("\\s+")[0];
        hand2 = hand2.split("\\s+")[0];
        int points1 = getPunti(hand1);
        int point2 = getPunti(hand2);
        if(points1 != point2)
            return points1 - point2;
        else
            for(int i = 0; i < 5; i++) {
                int ordinal1 = getOrdinal(CARD.class, hand1.charAt(i));
                int ordinal2 = getOrdinal(CARD.class, hand2.charAt(i));
                if(ordinal1 == ordinal2)
                    continue;
                return ordinal1 - ordinal2;
            }//for

        return -100;

    }//compareHand

    private static int compareHandJ(String hand1, String hand2) {

        hand1 = hand1.split("\\s+")[0];
        hand2 = hand2.split("\\s+")[0];
        int points1 = getPuntiJ(hand1);
        int point2 = getPuntiJ(hand2);
        if(points1 != point2)
            return points1 - point2;
        else
            for(int i = 0; i < 5; i++) {
                int ordinal1 = getOrdinal(CARD_J.class, hand1.charAt(i));
                int ordinal2 = getOrdinal(CARD_J.class, hand2.charAt(i));
                if(ordinal1 == ordinal2)
                    continue;
                return ordinal1 - ordinal2;
            }//for

        return -100;

    }//compareHandJ

    private static <T extends Enum<T>> int getOrdinal(Class<T> card_enum, char c) {
        for (Enum<T> val : card_enum.getEnumConstants())
            if (val.name().charAt(Character.isDigit(c) && val.name().length() == 2 ? 1 : 0) == c)
                return val.ordinal();
        return -1;
    }//getOrdinal

}//class
