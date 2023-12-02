package aoc2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day02 extends AOCUtils {
    public Day02() { super("2"); }

    // 12 red cubes, 13 green cubes, and 14 blue cubes
    @Override
    void solve(List<String> input) {
        Map<String, Integer> max = Map.of(
                "red", 12,
                "green", 13,
                "blue", 14
        );

        int sumId = 0;
        for(String row : input) {
            String[] game = row.split(": ");
            int gameNumber = Integer.parseInt(game[0].split(" ")[1]);
            String[] sets = game[1].split("; ");
            Boolean valid = true;
            for(String set : sets) {
                String[] cubes = set.split(", ");
                Map<String, Integer> countColor = new HashMap<>();
                countColor.put("red", 0);
                countColor.put("green", 0);
                countColor.put("blue", 0);
                for (String cube : cubes) {
                    Integer bag = Integer.valueOf(cube.split(" ")[0]);
                    String color = cube.split(" ")[1];
                    countColor.put(color, countColor.get(color) +  bag);
                }//for
                for(String colorKey : countColor.keySet()) {
                    if(countColor.get(colorKey) > max.get(colorKey))
                        valid = false;
                }//for
            }//for
            if(valid)
                sumId += gameNumber;
        }//for
        solution(sumId);
    }//solve

}//class
