package aoc2023;

import java.util.List;

public class Day06 extends AOCUtils {

    public Day06() { super("6"); }

    @Override
    void solve(List<String> input) {
        List<Integer> times = convertToInts(List.of(input.get(0).split(":\\s+")[1].split("\\s+")));
        List<Integer> distances = convertToInts(List.of(input.get(1).split(":\\s+")[1].split("\\s+")));
        solution(pt1(times, distances));

        long time = Long.parseLong(input.get(0).split(":\\s+")[1].replace(" ", ""));
        long distance = Long.parseLong(input.get(1).split(":\\s+")[1].replace(" ", ""));
        solution(pt2(time, distance));
    }//solve

    int pt1(List<Integer> times, List<Integer> distances) {
        int ris = 1;
        for(int i = 0; i < times.size(); i++) {
            int cnt = 0;
            for (int j = 0; j <= times.get(i); j++)
                cnt += (times.get(i) - j) * j > distances.get(i) ? 1 : 0;
            ris *= cnt;
        }//for
        return ris;
    }//pt1

    long pt2(long time, long distance) {
        int ris = 0;
        for (int j = 0; j <= time; j++) {
            ris += (time - j) * j > distance ? 1 : 0;
        }//for
        return ris;
    }//pt1
}//class
