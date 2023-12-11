package aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Day11 extends AOCUtils {
    public Day11() { super("11"); }

    private record Position(int r, int c){}

    @Override
    void solve(List<String> input) {

        List<Integer> emptyRows = expandRow(input);
        List<Integer> emptyCols = expandColumn(input);

        solution(calculate(input, emptyRows, emptyCols, 2));
        solution(calculate(input, emptyRows, emptyCols, 1000000));

    }//solve

    private static List<Integer> expandRow(List<String> input) {
        List<Integer> rows = new ArrayList<>();
        for(int r = 0; r < input.size(); r++)
            if(!input.get(r).contains("#"))
                rows.add(r);

        return rows;
    }//expandRow

    private static List<Integer> expandColumn(List<String> input) {
        int numCols = input.get(0).length();

        List<Integer> colsToRemove = new ArrayList<>();

        for(int c = 0; c < numCols; c++) {
            boolean shouldRemove = true;
            for(String s : input) {
                if (s.charAt(c) == '#') {
                    shouldRemove = false;
                    break;
                }//if
            }//for
            if (shouldRemove)
                colsToRemove.add(c);
        }//for

        return colsToRemove;
    }//expandCols


    private long calculate(List<String> grid, List<Integer> emptyRows, List<Integer> emptyCols, int scale) {
        List<Position> points = new ArrayList<>();
        long total = 0;

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
        return total;
    }//calculate

}//class
