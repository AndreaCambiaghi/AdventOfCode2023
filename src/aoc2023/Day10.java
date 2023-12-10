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

       Set<Position> loop = new HashSet<>();
        Queue<Position> q = new ArrayDeque<>();
        loop.add(new Position(sr, sc));
        q.add(new Position(sr, sc));

        while (!q.isEmpty()) {
            Position pair = q.poll();
            int r = pair.r;
            int c = pair.c;
            char ch = input.get(r).charAt(c);

            if (r > 0 && "S|JL".contains(String.valueOf(ch)) && "|7F".contains(String.valueOf(input.get(r - 1).charAt(c))) && !loop.contains(new Position(r - 1, c))) {
                loop.add(new Position(r - 1, c));
                q.add(new Position(r - 1, c));
            }//if

            if (r < input.size() - 1 && "S|7F".contains(String.valueOf(ch)) && "|JL".contains(String.valueOf(input.get(r + 1).charAt(c))) && !loop.contains(new Position(r + 1, c))) {
                loop.add(new Position(r + 1, c));
                q.add(new Position(r + 1, c));
            }//if

            if (c > 0 && "S-J7".contains(String.valueOf(ch)) && "-LF".contains(String.valueOf(input.get(r).charAt(c - 1))) && !loop.contains(new Position(r, c - 1))) {
                loop.add(new Position(r, c - 1));
                q.add(new Position(r, c - 1));
            }//if

            if (c < input.get(r).length() - 1 && "S-LF".contains(String.valueOf(ch)) && "-J7".contains(String.valueOf(input.get(r).charAt(c + 1))) && !loop.contains(new Position(r, c + 1))) {
                loop.add(new Position(r, c + 1));
                q.add(new Position(r, c + 1));
            }//if
        }//while

        solution(loop.size() / 2);

    }//solve

}//class
