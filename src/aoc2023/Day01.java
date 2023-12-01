package aoc2023;

import java.util.List;

public class Day01 extends AOCUtils {

    public Day01() { super("1"); }

    @Override
    void solve(List<String> input) {
        int sum = 0;
        for(String row : input) {
            String number = "";
            for(Character c : row.toCharArray())
                if(Character.isDigit(c))
                    number += c;
            number = number.substring(0, 1) + number.substring(number.length()-1);
            sum += Integer.parseInt(number);
        }
        solution(sum);
    }//solve

}//class
