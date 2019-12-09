package com.mikeconroy.adventofcode.day2;

import java.util.ArrayList;
import java.util.List;

public class IntCodeComputer {

    private List<Integer> program = new ArrayList<>();
    private List<Integer> initialProgram = new ArrayList<>();
    private int programCounter = 0;
    private int currentValue = 0;
    private int input = 1;
    private int output = 0;

    public void run(){
        currentValue = program.get(programCounter);
        int opcode = getOpcode(currentValue);
        do {
            int increaseBy = 4;
            int result = 0;

            if(opcode == 1){
                //ADDITION
                int value1 = 0;
                int value2 = 0;
                //Get first parameter mode
                if(getParameterMode(currentValue, 1) == ParameterModes.POSITION_MODE){
                    int valuePosition1 = program.get(programCounter + 1);
                    value1 = program.get(valuePosition1);
                } else {
                    value1 = program.get(programCounter + 1);
                }

                if(getParameterMode(currentValue, 0) == ParameterModes.POSITION_MODE){
                    int valuePosition2 = program.get(programCounter + 2);
                    value2 = program.get(valuePosition2);
                } else {
                    value2 = program.get(programCounter + 2);
                }
                int writeToPosition = program.get(programCounter + 3);

                result = value1 + value2;
                program.set(writeToPosition, result);
            } else if (opcode == 2){
                //MULTIPLICATION

                int value1 = 0;
                int value2 = 0;
                if(getParameterMode(currentValue, 1) == ParameterModes.POSITION_MODE){
                    int valuePosition1 = program.get(programCounter + 1);
                    value1 = program.get(valuePosition1);
                } else {
                    value1 = program.get(programCounter + 1);
                }

                if(getParameterMode(currentValue, 0) == ParameterModes.POSITION_MODE){
                    int valuePosition2 = program.get(programCounter + 2);
                    value2 = program.get(valuePosition2);
                } else {
                    value2 = program.get(programCounter + 2);
                }
                int writeToPosition = program.get(programCounter + 3);

                result = value1 * value2;
                program.set(writeToPosition, result);
            } else if(opcode == 3){
                //Take input and save to position of parameter.
                result = input;
                increaseBy = 2;
                int writeToPosition = program.get(programCounter + 1);
                program.set(writeToPosition, result);
            } else if(opcode == 4){
                //Output value of only 1 parameter.
                increaseBy = 2;

                if(getParameterMode(currentValue, 0) == ParameterModes.POSITION_MODE){                    
                    output = program.get(program.get(programCounter + 1));
                } else {
                    output = program.get(programCounter + 1);
                }
            }

            programCounter += increaseBy;
            currentValue = program.get(programCounter);
            opcode = getOpcode(currentValue);
        } while (opcode != 99);
    }

    private ParameterModes getParameterMode(int currentValue, int parameterPosition){
        String currentValueString = String.format("%04d", currentValue);
        int parameter = Integer.parseInt("" + currentValueString.charAt(parameterPosition));
        if(parameter == 0){
            return ParameterModes.POSITION_MODE;
        } else if (parameter == 1){
            return ParameterModes.IMMEDIATE_MODE;
        }
        assert(false);
        return ParameterModes.POSITION_MODE;
    }

    private int getOpcode(int operation){
        String operationString = String.format("%04d", operation);
        String subString = operationString.substring(operationString.length() - 2);
        return Integer.parseInt(subString);
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

    public int getOutput(){
        return output;
    }

    private enum ParameterModes {
        POSITION_MODE, IMMEDIATE_MODE;
    }

}