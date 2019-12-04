package com.mikeconroy.adventofcode.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.mikeconroy.adventofcode.Solver;

public class Day2Solver implements Solver {

    private String fileLocation = "inputs/day2";

    List<Integer> initialProgram = new ArrayList<>();

    @Override
    public int solve(int part) {
        loadInputIntoArray();
        if(part == 1){
            return calculateResultForPart1();
        } else if (part == 2){
            return calculateResultForPart2();
        } else {
            return 0;
        }
    }

    private int calculateResultForPart1(){
        IntCodeComputer computer = new IntCodeComputer();
        computer.loadProgram(initialProgram);
        computer.run();
        int result = computer.getProgram().get(0);
        computer.resetComputer();
        return result;
    }

    private int calculateResultForPart2(){
        IntCodeComputer computer = new IntCodeComputer();
        computer.loadProgram(initialProgram);
        for (int noun = 0; noun <= 99; noun++){
            for (int verb = 0; verb <= 99; verb++){
                // initialProgram = new ArrayList<Integer>();
                // loadInputIntoArray();
                // initialProgram.set(1, noun);
                // initialProgram.set(2, verb);
                computer.resetComputer();
                computer.setNoun(noun);
                computer.setVerb(verb);
                computer.run();

                if(computer.getProgram().get(0) == 19690720){
                    return (100*noun) + verb;
                }
            }
        }
        return 0;
    }

    private void loadInputIntoArray(){
        BufferedReader bufferedReader;
        try{
            File inputFile = new File(getClass().getClassLoader().getResource(fileLocation).getFile());

            bufferedReader = new BufferedReader(new FileReader(inputFile)); 
        
            String fileContents; 
            while ((fileContents = bufferedReader.readLine()) != null) {
                String[] values = fileContents.split(",");
                for (String value : values) {
                    initialProgram.add(Integer.parseInt(value));
                }
            }
            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}