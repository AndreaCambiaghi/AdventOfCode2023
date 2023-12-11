package aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Day11 extends AOCUtils {
    public Day11() { super("11"); }

    private record Position(int r, int c){}

    @Override
    void solve(List<String> input) {
        List<String> grid = new ArrayList<>(input);
        List<Integer> emptyRows = expandRow(input);
        List<Integer> emptyCols = convertToInts(expandColumn(input));

        List<Position> galaxyPositions = findGalaxyPositions(input);

        int sum = 0;
        for(int i = 0; i < galaxyPositions.size(); i++)
            for(int j = i + 1; j < galaxyPositions.size(); j++)
                sum += Math.abs(galaxyPositions.get(i).r - galaxyPositions.get(j).r) + Math.abs(galaxyPositions.get(i).c - galaxyPositions.get(j).c);

        solution(sum);

        List<Position> points = new ArrayList<>();
        long total = 0;
        int scale = 1000000;

        for(int r = 0; r < grid.size(); r++)
            for (int c = 0; c < grid.get(r).length(); c++)
                if (grid.get(r).charAt(c) == '#')
                    points.add(new Position(r, c));

        for (int i = 0; i < points.size(); i++) {
            Position point1 = points.get(i);
            int r1 = point1.r;
            int c1 = point1.c;

            for (int j = 0; j < i; j++) {
                Position point2 = points.get(j);
                int r2 = point2.r;
                int c2 = point2.c;

                for (int r = Math.min(r1, r2); r < Math.max(r1, r2); r++)
                    total += (emptyRows.contains(r) ? scale : 1);

                for (int c = Math.min(c1, c2); c < Math.max(c1, c2); c++)
                    total += (emptyCols.contains(c) ? scale : 1);
            }//for
        }//for

        solution(total);

    }//solve

    private static List<Integer> expandRow(List<String> input) {
        List<Integer> rows = new ArrayList<>();
        for(int r = 0; r < input.size(); r++)
            if(!input.get(r).contains("#"))
                rows.add(r);

        for(int i = 0; i < rows.size(); i++)
            input.add(rows.get(i) + i, ".".repeat(input.get(i).length()));

        return rows;
    }//expandRow

    private static List<String> expandColumn(List<String> input) {
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
        return cols;
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
