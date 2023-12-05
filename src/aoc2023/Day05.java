package aoc2023;

import java.math.BigInteger;
import java.util.*;

public class Day05 extends AOCUtils {
    public Day05() { super("5"); }

    @Override
    void solve(List<String> input) {

        Map<BigInteger, BigInteger> seeds = new LinkedHashMap<>();
        for (String seed : input.get(0).split(": ")[1].split(" "))
            seeds.put(new BigInteger(seed), new BigInteger(seed));
        input.remove(0);
        input.remove(1);

        for (int i = 0; i < input.size(); ) {
            List<BigInteger> excluded = new ArrayList<>();
            while (!input.get(i).contains("map")) {

                if (input.get(i).isBlank()) {
                    i++;
                    continue;
                } // if

                List<BigInteger> rowNumber = convertToBigIntegers(List.of(input.get(i).split(" ")));
                System.out.println(rowNumber);

                for (BigInteger key : seeds.keySet()) {
                    System.out.println("AAA" + rowNumber + " " + seeds.get(key) + "key: " + key);
                    if (
                        !excluded.contains(key) &&
                        rowNumber.get(1).compareTo(seeds.get(key)) <= 0 &&
                        rowNumber.get(1).add(rowNumber.get(2)).compareTo(seeds.get(key)) > 0
                    ) {
                        excluded.add(key);
                        System.out.println(seeds.get(key) + " " + rowNumber.get(1) + " " + rowNumber.get(0));
                        seeds.put(key, seeds.get(key).add(rowNumber.get(0).subtract(rowNumber.get(1))));
                    } // if

                } // for

                i++;
                if (i >= input.size())
                    break;

            } // while
            System.out.println(seeds);

            i++;

        } // for

        solution(seeds.values().stream().min(BigInteger::compareTo).orElse(BigInteger.ZERO).toString());

    } // solve

    private static List<BigInteger> convertToBigIntegers(List<String> strings) {
        List<BigInteger> bigIntegers = new ArrayList<>();
        for (String str : strings) {
            bigIntegers.add(new BigInteger(str));
        }
        return bigIntegers;
    }

}//class
