package aoc2023;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AOCPuzzle {

    public AOCPuzzle(String day) {
        File file = new File("in/day" + day + ".txt");
        if(!file.exists()) {
            solve(new ArrayList<>());
            return;
        }//if

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch(FileNotFoundException e) {
            System.err.println("File not found!!");
            solve(new ArrayList<>());
            return;
        }//catch

        List<String> inputLines = new ArrayList<>();
        try {
            String line;
            while((line = reader.readLine()) != null)
                inputLines.add(line);

            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }//catch

        solve(inputLines);
    }//AOCPuzzle

    abstract void solve(List<String> input);

    public List<Integer> convertToInts(List<String> input) {
        List<Integer> ints = new ArrayList<>();
        for(String s : input)
            ints.add(Integer.parseInt(s));
        return ints;
    }//convertToInts

    public List<Long> convertToLongs(List<String> input) {
        List<Long> ints = new ArrayList<>();
        for(String s : input)
            ints.add(Long.parseLong(s));
        return ints;
    }//convertToLongs

}//class