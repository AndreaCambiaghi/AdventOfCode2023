package aoc2023;

import java.util.*;

public class Day07 extends AOCUtils {
    public Day07() { super("7"); }

    public enum CARD {
        A, K, Q, J, T, _9, _8, _7, _6, _5, _4, _3, _2;
    }//CARD


    @Override
    void solve(List<String> input) {
        input.sort(Day07::compareHand);
        int i = input.size();
        int ris = 0;
        for(String row : input) {
            int points = Integer.parseInt(row.split("\\s+")[1]);
            ris += (points * i--);
        }//for
        solution(ris);
    }//solve

    private static int getPunti(String cards) {
        return fiveOfKind(cards);
    }//getPunti

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

        // SE IL PRIMO MAGGIORE ALLORA > 0
        // SE SONO UGUALI ALLORA = 0
        // SE IL SECONDO MAGGIORE ALLORA < 0
        hand1 = hand1.split("\\s+")[0];
        hand2 = hand2.split("\\s+")[0];
        int points1 = getPunti(hand1);
        int point2 = getPunti(hand2);
        if(points1 > point2)
            return 1;
        else if(points1 < point2)
            return -1;
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

    private static <T extends Enum<T>> int getOrdinal(Class<T> card, char c) {
        for (Enum<T> val : card.getEnumConstants())
            if (val.name().charAt(Character.isDigit(c) && val.name().length() == 2 ? 1 : 0) == c)
                return val.ordinal();
        return -1;
    }//getOrdinal

}//class
