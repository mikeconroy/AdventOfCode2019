package com.mikeconroy.adventofcode.day2;

import java.util.ArrayList;
import java.util.List;

public class IntCodeComputer {

    private List<Integer> program = new ArrayList<>();
    private List<Integer> initialProgram = new ArrayList<>();
    private int programCounter = 0;
    private int currentValue = 0;

    public void run(){
        currentValue = program.get(programCounter);
        do {
            int valuePosition1 = program.get(programCounter + 1);
            int valuePosition2 = program.get(programCounter + 2);
            int value1 = program.get(valuePosition1);
            int value2 = program.get(valuePosition2);
            int writeToPosition = program.get(programCounter + 3);

            int result = 0;

            if(currentValue == 1){
                //ADDITION
                result = value1 + value2;
            } else if (currentValue == 2){
                //MULTIPLICATION
                result = value1 * value2;
            }

            program.set(writeToPosition, result);

            programCounter += 4;
            currentValue = program.get(programCounter);
        } while (currentValue != 99);
    }

    public void resetComputer(){
        programCounter = 0;
        currentValue = 0;
        program = new ArrayList<Integer>();
        program.addAll(initialProgram);
    }

    public void setNoun(int noun){
        program.set(1, noun);
    }

    public void setVerb(int verb){
        program.set(2, verb);
    }

    public void loadProgram(List<Integer> program){
        resetComputer();
        this.program = new ArrayList<Integer>();
        this.initialProgram = new ArrayList<Integer>();
        this.program.addAll(program);
        this.initialProgram.addAll(program);
    }

    public List<Integer> getProgram(){
        return program;
    }

}