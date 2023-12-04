package aoc2023;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day04 extends AOCUtils {
    public Day04() { super("4"); }

    @Override
    void solve(List<String> input) {

        int points = 0;
        Map<Integer, Integer> scratchcards = new LinkedHashMap<>();
        int cnt = 1;
        for(String card : input) {

            List<Integer> winnerNumber = convertToInts(List.of(card.split(":\\s+")[1].split(" \\|\\s+")[0].split("\\s+")));
            List<Integer> myNumber = convertToInts(List.of(card.split(":\\s+")[1].split(" \\|\\s+")[1].split("\\s+")));
            int diff = (int)winnerNumber.stream().filter(myNumber::contains).count();

            points += (int) Math.pow(2.0, (diff-1) );
            if(!scratchcards.containsKey(cnt))
                scratchcards.put(cnt, 1);
            else
                scratchcards.put(cnt, scratchcards.get(cnt) + 1);

            for(int j = 0; j < scratchcards.get(cnt); j++)
                for(int i = cnt + 1 ; i <= cnt + diff; i++)
                    if(!scratchcards.containsKey(i))
                        scratchcards.put(i, 1);
                    else
                        scratchcards.put(i, scratchcards.get(i) + 1);
            cnt++;
        }//for
        int ris = scratchcards.values().stream().mapToInt(Integer::intValue).sum();
        solution(points);
        solution(ris);
    }//solve
}//class
