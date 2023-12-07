package aoc2023;

import java.util.*;

public class Day05 extends AOCUtils {
    public Day05() { super("5"); }
    @Override
    void solve(List<String> input) {

        List<Long> seeds = Arrays.stream(input.get(0).split(": ")[1].split(" ")).map(Long::valueOf).toList();
        input.remove(0);

        solution(pt1(input, seeds));

        List<Pair> seedsPair = new ArrayList<>();
        for(int i = 0; i < seeds.size(); i+=2)
            seedsPair.add(new Pair(seeds.get(i), seeds.get(i) + seeds.get(i+1)));

        solution(pt2(input, seedsPair));

    }//solve

    long pt1(List<String> input, List<Long> seeds) {
        long min = Long.MAX_VALUE;

        for (Long key : seeds) {
            Long curr = key;
            for (int i = 0; i < input.size(); ) {
                List<Long> excluded = new ArrayList<>();
                while (!input.get(i).contains("map")) {

                    if (input.get(i).isBlank()) {
                        i++;
                        continue;
                    }//if

                    List<Long> rowNumber = convertToLongs(List.of(input.get(i).split(" ")));

                    if (
                    !excluded.contains(key) &&
                    rowNumber.get(1) <= curr &&
                    rowNumber.get(1) + rowNumber.get(2) > curr
                    ) {
                        excluded.add(key);
                        curr = curr + rowNumber.get(0) - rowNumber.get(1);
                    }//if

                    i++;
                    if (i >= input.size())
                        break;

                }//while

                i++;

            }//for
            if (min > curr)
                min = curr;

        }//for
        return min;
    }//pt1

    record Pair(long start, long end){}
    record Triple(long fst, long snd, long thr){}

    long pt2(List<String> input, List<Pair> seeds) {

        List<String> blocks = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        input.subList(1, input.size()).forEach(row -> {
            sb.append(row).append("\n");
            if (row.isBlank()) {
                blocks.add(sb.toString());
                sb.setLength(0);
            }//if
        });
        blocks.add(sb.toString());

        for(String block : blocks) {
            List<Triple> ranges = new ArrayList<>();
            List<Pair> pairList;

            for(String line : List.of(block.split("\n")).subList(1, block.split("\n").length))
                ranges.add(new Triple(Long.parseLong(line.split(" ")[0]), Long.parseLong(line.split(" ")[1]), Long.parseLong(line.split(" ")[2])));

            pairList = new ArrayList<>();
            while(!seeds.isEmpty()) {
                Pair p = seeds.remove(0);
                long s = p.start;
                long e = p.end;

                boolean foundMatch = false;
                for(Triple triple : ranges) {

                    long overlapping_start = Long.max(s, triple.snd);
                    long overlapping_end = Long.min(e, (triple.snd + triple.thr));
                    if(overlapping_start < overlapping_end) {
                        pairList.add(new Pair((overlapping_start - triple.snd + triple.fst), (overlapping_end - triple.snd + triple.fst)));
                        if(overlapping_start > s)
                            seeds.add(new Pair(s, overlapping_start));
                        if(e > overlapping_end)
                            seeds.add(new Pair(overlapping_end, e));
                        foundMatch = true;
                        break;
                    }//if

                }//for

                if(!foundMatch)
                    pairList.add(new Pair(s, e));

            }//while
            seeds = pairList;
        }//for
        return seeds.stream().mapToLong(Pair::start).min().orElse(0);
    }//pt2

}//class
