package aoc2023;

import java.util.*;

public class Day10 extends AOCUtils {
    public Day10() { super("10"); }

    private record Position(int r, int c) {}

    @Override
    void solve(List<String> input) {

        int sr = 0, sc = 0;

        for (int r = 0; r < input.size(); r++)
            for (int c = 0; c < input.get(r).length(); c++)
                if (input.get(r).charAt(c) == 'S') {
                    sr = r;
                    sc = c;
                    r = input.size();
                    break;
                }//if

        solution(part1(input, sr, sc));
        solution(part2(input, sr, sc));

    }//solve

    private int part1(List<String> grid, int sr, int sc) {

        Set<Position> loop = new HashSet<>();
        Queue<Position> q = new ArrayDeque<>();
        loop.add(new Position(sr, sc));
        q.add(new Position(sr, sc));

        while (!q.isEmpty()) {
            Position Position = q.poll();
            int r = Position.r;
            int c = Position.c;
            char ch = grid.get(r).charAt(c);

            if (r > 0 && "S|JL".contains(String.valueOf(ch)) && "|7F".contains(String.valueOf(grid.get(r - 1).charAt(c))) && !loop.contains(new Position(r - 1, c))) {
                loop.add(new Position(r - 1, c));
                q.add(new Position(r - 1, c));
            }//if

            if (r < grid.size() - 1 && "S|7F".contains(String.valueOf(ch)) && "|JL".contains(String.valueOf(grid.get(r + 1).charAt(c))) && !loop.contains(new Position(r + 1, c))) {
                loop.add(new Position(r + 1, c));
                q.add(new Position(r + 1, c));
            }//if

            if (c > 0 && "S-J7".contains(String.valueOf(ch)) && "-LF".contains(String.valueOf(grid.get(r).charAt(c - 1))) && !loop.contains(new Position(r, c - 1))) {
                loop.add(new Position(r, c - 1));
                q.add(new Position(r, c - 1));
            }//if

            if (c < grid.get(r).length() - 1 && "S-LF".contains(String.valueOf(ch)) && "-J7".contains(String.valueOf(grid.get(r).charAt(c + 1))) && !loop.contains(new Position(r, c + 1))) {
                loop.add(new Position(r, c + 1));
                q.add(new Position(r, c + 1));
            }//if
        }//while

        return loop.size() / 2;

    }//part1

    private int part2(List<String> grid, int sr, int sc) {

        Set<Position> loop = new HashSet<>(Set.of(new Position(sr, sc)));
        Deque<Position> q = new ArrayDeque<>();
        q.add(new Position(sr, sc));

        Set<String> possibleValidMoves = new HashSet<>(Set.of("|", "-", "J", "L", "7", "F"));

        while (!q.isEmpty()) {
            Position current = q.poll();
            int r = current.r;
            int c = current.c;
            char ch = grid.get(r).charAt(c);

            // Logica per spostarsi in alto
            if (r > 0 && "S|JL".contains(String.valueOf(ch)) && "|7F".contains(String.valueOf(grid.get(r - 1).charAt(c))) && !loop.contains(new Position(r - 1, c))) {
                loop.add(new Position(r - 1, c));
                q.add(new Position(r - 1, c));
                if (ch == 'S')
                    possibleValidMoves.retainAll(Set.of("|", "J", "L"));
            }//if

            // Logica per spostarsi in basso
            if (r < grid.size() - 1 && "S|7F".contains(String.valueOf(ch)) && "|JL".contains(String.valueOf(grid.get(r + 1).charAt(c))) && !loop.contains(new Position(r + 1, c))) {
                loop.add(new Position(r + 1, c));
                q.add(new Position(r + 1, c));
                if (ch == 'S')
                    possibleValidMoves.retainAll(Set.of("|", "7", "F"));
            }//if

            // Logica per spostarsi a sinistra
            if (c > 0 && "S-J7".contains(String.valueOf(ch)) && "-LF".contains(String.valueOf(grid.get(r).charAt(c - 1))) && !loop.contains(new Position(r, c - 1))) {
                loop.add(new Position(r, c - 1));
                q.add(new Position(r, c - 1));
                if (ch == 'S')
                    possibleValidMoves.retainAll(Set.of("-", "J", "7"));
            }//if

            // Logica per spostarsi a destra
            if (c < grid.get(r).length() - 1 && "S-LF".contains(String.valueOf(ch)) && "-J7".contains(String.valueOf(grid.get(r).charAt(c + 1))) && !loop.contains(new Position(r, c + 1))) {
                loop.add(new Position(r, c + 1));
                q.add(new Position(r, c + 1));
                if (ch == 'S')
                    possibleValidMoves.retainAll(Set.of("-", "L", "F"));
            }//if
        }//for

        String S = possibleValidMoves.iterator().next();

        List<String> modifiedGrid = new ArrayList<>();
        for (String row : grid) {
            String modifiedRow = row.replace("S", S);
            modifiedGrid.add(modifiedRow);
        }//for
        grid = new ArrayList<>(modifiedGrid);

        modifiedGrid = new ArrayList<>();
        for (int r = 0; r < grid.size(); r++) {
            StringBuilder modifiedRow = new StringBuilder();

            for (int c = 0; c < grid.get(r).length(); c++) {
                if (loop.contains(new Position(r, c)))
                    modifiedRow.append(grid.get(r).charAt(c));
                else
                    modifiedRow.append(".");
            }//for

            modifiedGrid.add(modifiedRow.toString());
        }//for

        grid = new ArrayList<>(modifiedGrid);

        Set<Position> unionSet = getPositions(grid);
        unionSet.addAll(loop);
        return grid.size() * grid.get(0).length() - unionSet.size();

    }//part2

    private static Set<Position> getPositions(List<String> grid) {
        List<Position> outside = new ArrayList<>();
        for (int r = 0; r < grid.size(); r++) {
            boolean within = false;;
            boolean up = false;
            for (int c = 0; c < grid.get(r).length(); c++) {
                char ch = grid.get(r).charAt(c);
                if (ch == '|')
                    within = !within;
                else if ("LF".contains(Character.toString(ch)))
                    up = ch == 'L';
                else if ("7J".contains(Character.toString(ch)))
                    if (ch != (up ? 'J' : '7'))
                        within = !within;
                if (!within)
                    outside.add(new Position(r, c));
            }//for c
        }//for r

        return new HashSet<>(outside);
    }//getPosition


}//class
