package aoc2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day08 extends AOCUtils {
    public Day08() {
        super("8");
    }

    private record Node(String l, String r) {
    }

    @Override
    void solve(List<String> input) {

        String cmd = input.remove(0);
        input.remove(0);

        Map<String, Node> nodes = fillMap(input);

        solution(part1(nodes, cmd));
        solution(part2(nodes, cmd));

    }//solve

    private static Map<String, Node> fillMap(List<String> input) {
        Map<String, Node> nodes = new HashMap<>();
        for (String row : input) {
            row = row.replaceAll("[=,()]", "");
            String[] node = row.split("\\s+");
            nodes.put(node[0], new Node(node[1], node[2]));
        }//for
        return nodes;
    }//fillMap

    private static int part1(Map<String, Node> nodes, String cmd) {
        String curr = "AAA";
        int idx = 0;
        while (!curr.equals("ZZZ")) {
            char lf = cmd.charAt(idx % cmd.length());
            if (lf == 'L')
                curr = nodes.get(curr).l;
            else if (lf == 'R')
                curr = nodes.get(curr).r;
            idx++;
        }//while
        return idx;
    }//part1

    private static long part2(Map<String, Node> nodes, String cmd) {
        List<Long> idxs = new ArrayList<>();
        List<String> currs = new ArrayList<>();
        for (String node : nodes.keySet())
            if (node.endsWith("A"))
                currs.add(node);

        for (String c : currs) {
            int idx = 0;
            while (!c.endsWith("Z")) {
                char lf = cmd.charAt(idx % cmd.length());
                if (lf == 'L')
                    c = nodes.get(c).l;
                else if (lf == 'R')
                    c = nodes.get(c).r;
                idx++;
            }//while
            idxs.add((long) idx);
        }//for
        return mcmList(idxs);
    }//class

    public static long mcm(long a, long b) {
        long mcd = gcd(a, b);
        return (a * b) / mcd;
    }//mcm

    public static long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }//gcd

    public static long mcmList(List<Long> list) {
        long result = list.get(0);
        for (int i = 1; i < list.size(); i++)
            result = mcm(result, list.get(i));
        return result;
    }//mcmList

}//class
