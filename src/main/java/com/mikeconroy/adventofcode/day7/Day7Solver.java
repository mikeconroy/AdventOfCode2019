package com.mikeconroy.adventofcode.day7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mikeconroy.adventofcode.InputReader;
import com.mikeconroy.adventofcode.Solver;

public class Day7Solver implements Solver {

    private String fileLocation = "inputs/day7";
    List<Integer> initialProgram;

    public int solve(int part){
        loadInput();
        if(part == 1){
            return calculatePart1();
        } else if(part == 2){
            return 0;
        }
        return 0;
    }

    private int calculatePart1(){
        Amplifier ampA = new Amplifier(initialProgram);
        Amplifier ampB = new Amplifier(initialProgram);
        Amplifier ampC = new Amplifier(initialProgram);
        Amplifier ampD = new Amplifier(initialProgram);
        Amplifier ampE = new Amplifier(initialProgram);

        int max = 0;
        String maxPhaseSetting = "";
        for(int a = 0; a < 5; a++){
            for(int b = 0; b < 5; b++){
                for(int c = 0; c < 5; c++){
                    for(int d = 0; d < 5; d++){
                        for(int e = 0; e < 5; e++){
                            Set<Integer> settingsSet = new HashSet<Integer>();
                            if(settingsSet.add(a) &&
                                settingsSet.add(b) &&
                                settingsSet.add(c) &&
                                settingsSet.add(d) &&
                                settingsSet.add(e)){
                                    ampA.setPhaseSetting(a);
                                    ampB.setPhaseSetting(b);
                                    ampC.setPhaseSetting(c);
                                    ampD.setPhaseSetting(d);
                                    ampE.setPhaseSetting(e);                                 
                                    
                                    ampA.setInputSignal(0);
                                    ampB.setInputSignal(ampA.getOutput());
                                    ampC.setInputSignal(ampB.getOutput());
                                    ampD.setInputSignal(ampC.getOutput());
                                    ampE.setInputSignal(ampD.getOutput());
        
                                    int output = ampE.getOutput();
                                    if(output > max){
                                        max = output;
                                    }
                            }
                        }
                    }
                }
            }
        }
        return max;
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