package aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Day11 extends AOCUtils {
    public Day11() { super("11"); }

    private record Position(int r, int c){}

    @Override
    void solve(List<String> input) {
        expandRow(input);
        expandColumn(input);

        List<Position> galaxyPositions = findGalaxyPositions(input);

        int sum = 0;
        for(int i = 0; i < galaxyPositions.size(); i++)
            for(int j = i + 1; j < galaxyPositions.size(); j++)
                sum += Math.abs(galaxyPositions.get(i).r - galaxyPositions.get(j).r) + Math.abs(galaxyPositions.get(i).c - galaxyPositions.get(j).c);

        solution(sum);

    }//solve

    private static void expandRow(List<String> input) {
        List<Integer> rows = new ArrayList<>();
        for(int r = 0; r < input.size(); r++)
            if(!input.get(r).contains("#"))
                rows.add(r);

        for(int i = 0; i < rows.size(); i++)
            input.add(rows.get(i) + i, ".".repeat(input.get(i).length()));
    }//expandRow

    private static void expandColumn(List<String> input) {
        List<String> cols = new ArrayList<>();
        for (int i = 0; i < input.get(0).length(); i++)
            cols.add(i + "");

        for(String string : input)
            for(int c = 0; c < string.length(); c++)
                if(string.charAt(c) == '#')
                    cols.remove(c + "");


        List<String> modifiedInput = new ArrayList<>();

        for(String s : input) {
            StringBuilder newRow = new StringBuilder(s);
            for(int i = 0; i < cols.size(); i++)
                newRow.insert(Integer.parseInt(cols.get(i)) + i, ".");
            modifiedInput.add(newRow.toString());
        }//for

        input.clear();
        input.addAll(modifiedInput);
    }//expandColumn

    private static List<Position> findGalaxyPositions(List<String> input) {
        List<Position> galaxyPositions = new ArrayList<>();

        for (int r = 0; r < input.size(); r++)
            for (int c = 0; c < input.get(r).length(); c++)
                if (input.get(r).charAt(c) == '#')
                    galaxyPositions.add(new Position(r, c));
        return galaxyPositions;
    }//findGalaxyPositions

}//class
