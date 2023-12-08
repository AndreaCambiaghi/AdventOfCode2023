package aoc2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day08 extends AOCUtils {
    public Day08() { super("8"); }

    private record Node(String l, String r){}

    @Override
    void solve(List<String> input) {

        String cmd = input.remove(0);
        input.remove(0);
        Map<String, Node> nodes = new HashMap<>();
        for(String row : input) {
            row = row.replaceAll("[=,()]", "");
            String[] node = row.split("\\s+");
            nodes.put(node[0], new Node(node[1], node[2]));
        }//for
        String curr = "AAA";
        int idx = 0;
        while (!curr.equals("ZZZ")) {
            char lf = cmd.charAt(idx % cmd.length());
            if(lf == 'L')
                curr = nodes.get(curr).l;
            else if(lf == 'R')
                curr = nodes.get(curr).r;
            idx++;
        }//while

        solution(idx);

    }//solve


}//class
