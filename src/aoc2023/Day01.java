package aoc2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day01 extends AOCUtils {

    public Day01() { super("1"); }

    @Override
    void solve(List<String> input) {

        solution(sum(input));

        Map<String, String> map = Map.of(
                "one", "o1e",
                "two", "t2o",
                "three", "t3e",
                "four", "f4r",
                "five", "f5e",
                "six", "s6x",
                "seven", "s7n",
                "eight", "e8t",
                "nine", "n9e"
        );

        List<String> conv = new ArrayList<>();
        for(String row : input) {
            for (String key : map.keySet())
                row = row.replace(key, map.get(key));
            conv.add(row);
        }//for

        solution(sum(conv));

    }//solve

    int sum(List<String> rows) {
        int sum = 0;
        for(String row : rows) {
            StringBuilder number = new StringBuilder();
            for(Character c : row.toCharArray())
                if(Character.isDigit(c))
                    number.append(c).append(c);
            number = new StringBuilder(number.substring(0, 1) + number.substring(number.length() - 1));
            sum += Integer.parseInt(number.toString());
        }//for
        return sum;
    }//sum

}//class
