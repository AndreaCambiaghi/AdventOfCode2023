package aoc2023;

import java.util.List;

public class Day04 extends AOCUtils {
    public Day04() { super("4"); }

    @Override
    void solve(List<String> input) {
        int points = 0;
        for(String card : input) {
            List<Integer> winnerNumber = convertToInts(List.of(card.split(":\\s+")[1].split(" \\|\\s+")[0].split("\\s+")));
            List<Integer> myNumber = convertToInts(List.of(card.split(":\\s+")[1].split(" \\|\\s+")[1].split("\\s+")));
            int diff = (int)winnerNumber.stream().filter(myNumber::contains).count();
            points += (int) Math.pow(2.0, (diff-1) );
        }//for
        solution(points);
    }//solve
}//class
