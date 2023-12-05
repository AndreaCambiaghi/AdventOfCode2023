package aoc2023;

import java.math.BigInteger;
import java.util.*;

public class Day05 extends AOCUtils {
    public Day05() { super("5"); }

    @Override
    void solve(List<String> input) {

        List<Long> seeds = new ArrayList<>();
        for (String seed : input.get(0).split(": ")[1].split(" "))
            seeds.add(Long.valueOf(seed));
        input.remove(0);
        input.remove(1);

        long min = Long.MAX_VALUE;

        for (Long key : seeds) {
            Long curr = key;
            for (int i = 0; i < input.size(); ) {
                List<Long> excluded = new ArrayList<>();
                while (!input.get(i).contains("map")) {

                    if (input.get(i).isBlank()) {
                        i++;
                        continue;
                    }//if

                    List<Long> rowNumber = convertToLongs(List.of(input.get(i).split(" ")));

                    if (
                            !excluded.contains(key) &&
                                    rowNumber.get(1) <= curr &&
                                    rowNumber.get(1) + rowNumber.get(2) > curr
                    ) {
                        excluded.add(key);
                        curr = curr + rowNumber.get(0) - rowNumber.get(1);
                    }//if

                    i++;
                    if (i >= input.size())
                        break;

                }//while

                i++;

            }//for
            if (min > curr)
                min = curr;

        }//for

        solution(min);

    }//solve

}//class
