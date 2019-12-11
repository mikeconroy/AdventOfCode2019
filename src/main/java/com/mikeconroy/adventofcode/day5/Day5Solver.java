package com.mikeconroy.adventofcode.day5;

import java.util.ArrayList;
import java.util.List;

import com.mikeconroy.adventofcode.InputReader;
import com.mikeconroy.adventofcode.Solver;
import com.mikeconroy.adventofcode.day2.IntCodeComputer;

public class Day5Solver implements Solver {

    private String fileLocation = "inputs/day5";

    List<Integer> initialProgram;

    public int solve(int part){
        loadInput();
        if(part == 1){
            return calculateResultForPart1();
        } else if (part == 2){
            return calculateResultForPart2();
        }
        return 0;
    }

    private int calculateResultForPart1(){
        IntCodeComputer computer = new IntCodeComputer();
        computer.loadProgram(initialProgram);
        computer.run();
        int result = computer.getOutput();
        computer.resetComputer();
        return result;
    }
    
    private int calculateResultForPart2(){
        IntCodeComputer computer = new IntCodeComputer();
        computer.loadProgram(initialProgram);
        computer.run(5);
        int result = computer.getOutput();
        computer.resetComputer();
        return result;
    }

    private void loadInput(){
        initialProgram = new ArrayList<Integer>();

        String[] lines = InputReader.readFileLines(fileLocation);

        for(String line : lines){
            String[] values = line.split(",");
            for(String value : values){
                initialProgram.add(Integer.parseInt(value));
            }
        }
    }

}