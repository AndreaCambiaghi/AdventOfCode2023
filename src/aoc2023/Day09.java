package aoc2023;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day09 extends AOCUtils {
    public Day09() { super("9"); }

    @Override
    void solve(List<String> input) {

        int ris = 0;
        for(String row : input) {

            List<List<Integer>> sequences = new ArrayList<>();
            List<Integer> original = convertToInts(List.of(row.split("\\s+")));
            sequences.add(original);
            List<Integer> newSequence = new ArrayList<>();
            while(!original.stream().allMatch(n -> n == 0)) {
                for (int i = 0; i < original.size() - 1; i++) {
                    newSequence.add(original.get(i + 1) - original.get(i));
                }//for
                sequences.add(newSequence);
                original = newSequence;
                newSequence = new ArrayList<>();
            }//while

            sequences.get(sequences.size()-1).add(0);
            for(int i = sequences.size() - 2; i > 0; i--) {
                sequences.get(i-1).add(sequences.get(i-1).get(sequences.get(i-1).size()-1) + sequences.get(i).get(sequences.get(i).size()-1));
            }//for

            ris += sequences.get(0).get(sequences.get(0).size()-1);
        }//for
        solution(ris);
    }//solve

}//class
