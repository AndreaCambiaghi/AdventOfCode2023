package aoc2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day02 extends AOCUtils {
    public Day02() { super("2"); }

    // 12 red cubes, 13 green cubes, and 14 blue cubes
    @Override
    void solve(List<String> input) {

        Map<String, Integer> maxValid = Map.of(
                "red", 12,
                "green", 13,
                "blue", 14
        );

        int sumId = 0;
        int power = 0;
        for(String row : input) {

            String[] game = row.split(": ");
            int gameNumber = Integer.parseInt(game[0].split(" ")[1]);
            String[] sets = game[1].split("; ");

            Map<String, Integer> maxSetColor = new HashMap<>(Map.of(
                    "red", 0,
                    "green", 0,
                    "blue", 0
            ));

            for(String set : sets) {
                String[] cubes = set.split(", ");
                for (String cube : cubes) {
                    int bag = Integer.parseInt(cube.split(" ")[0]);
                    String color = cube.split(" ")[1];
                    maxSetColor.put(color, Integer.max(maxSetColor.get(color), bag));
                }//for
            }//for

            int gamePower = 1;
            for(String key : maxSetColor.keySet())
                gamePower *= maxSetColor.get(key);

            boolean valid = true;
            for(String key : maxSetColor.keySet())
                if(maxSetColor.get(key) > maxValid.get(key))
                    valid = false;

            if(valid)
                sumId += gameNumber;
            power += gamePower;
        }//for

        solution(sumId);
        solution(power);

    }//solve

}//class
