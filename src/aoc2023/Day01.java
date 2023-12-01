package aoc2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day01 extends AOCUtils {

    public Day01() { super("1"); }

    @Override
    void solve(List<String> input) {
        int sum = 0;
        /*for(String row : input) {
            String number = "";
            for(Character c : row.toCharArray())
                if(Character.isDigit(c))
                    number += c;
            number = number.substring(0, 1) + number.substring(number.length()-1);
            sum += Integer.parseInt(number);
        }*/
        solution(sum);

        sum = 0;
        Map<String, Integer> map = Map.of(
                "one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9
        );

        List<String> conv = new ArrayList<>();
        for(String row : input) {
            String app = "";
            char[] rowArray = row.toCharArray();
            String subString = "";
            for(Character c : rowArray) {
                subString += c;
                if(Character.isAlphabetic(c))
                    app += c;
                else if(Character.isDigit(c)) {
                    app = "";
                    continue;
                }
                for(int i = 0; i < app.length(); i++) {
                    if(map.containsKey(app.substring(i))) {
                        subString = subString.replace(app.substring(i, app.length()-1), map.get(app.substring(i)).toString());
                        app = app.substring(app.length()-1);
                        break;
                    }
                }
            }
            conv.add(subString);
        }

        for(String row : conv) {
            String number = "";
            for(Character c : row.toCharArray())
                if(Character.isDigit(c))
                    number += c;
            number = number.substring(0, 1) + number.substring(number.length()-1);
            sum += Integer.parseInt(number);
        }
        System.out.println(conv);
        solution(sum);

    }//solve

}//class
