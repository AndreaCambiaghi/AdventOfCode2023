package aoc2023;

import java.util.*;

public class Day03 extends AOCUtils {
    private static class Position {
        List<Integer> x;
        int y;

        Position(List<Integer> x, int y) {
            this.x = new ArrayList<>(x);
            this.y = y;
        }//Position

        @Override
        public String toString() {
            return "x: " + x.toString() + " y: " + y;
        }//toString
    }//class

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }//Position

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }//toString
    }//class

    public Day03() { super("3"); }

    @Override
    void solve(List<String> input) {
        Map<String, Position> indexNumber = new LinkedHashMap<>();
        Map<Point, Character> indexSymbol = new LinkedHashMap<>();
        int j = 0;

        for (String row : input) {
            StringBuilder number = new StringBuilder();
            List<Integer> index = new ArrayList<>();
            int i = 0;
            int cnt = 0;

            for (Character c : row.toCharArray()) {
                if (Character.isDigit(c)) {
                    number.append(c);
                    index.add(i);
                } else if (!number.isEmpty()) {
                    cnt++;
                    number.append("R").append(j).append("N").append(cnt);
                    indexNumber.put(number.toString(), new Position(index, j));
                    number = new StringBuilder();
                    index = new ArrayList<>();
                }//else if

                if (!Character.isDigit(c) && c != '.') indexSymbol.put(new Point(i, j), c);
                i++;
            }//for

            if (!number.isEmpty()) {
                cnt++;
                number.append("R").append(j).append("N").append(cnt);
                indexNumber.put(number.toString(), new Position(index, j));
            }//if

            j++;
        }//for

        int sum = 0;
        int sum2 = 0;
        for (Map.Entry<Point, Character> map : indexSymbol.entrySet()) {
            int x = map.getKey().x;
            int y = map.getKey().y;
            List<Integer> adjacent = new ArrayList<>();
            for (Map.Entry<String, Position> entry : indexNumber.entrySet()) {
                String key = entry.getKey();
                int n = Integer.parseInt(key.split("R")[0]);
                int yN = entry.getValue().y;
                List<Integer> indices = entry.getValue().x;

                for (Integer inx : indices) {
                    if ((Math.abs(yN - y) <= 1 && Math.abs(inx - x) <= 1)) {
                        sum += n;
                        adjacent.add(n);
                        break;
                    }//if
                }//for
            }//for
            if(adjacent.size() == 2)
                sum2 += (adjacent.get(0) * adjacent.get(1));
        }//for

        solution(sum);
        solution(sum2);
    }//solve

}//class
