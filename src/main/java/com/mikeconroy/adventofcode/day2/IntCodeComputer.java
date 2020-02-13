package com.mikeconroy.adventofcode.day2;

import java.util.ArrayList;
import java.util.List;

public class IntCodeComputer {

    private List<Integer> program = new ArrayList<>();
    private List<Integer> initialProgram = new ArrayList<>();
    private int programCounter = 0;
    private int currentValue = 0;
    private int input = 1;
    private int secondInput = 0;
    private int output = 0;

    public void run(){
        programCounter = 0;
        currentValue = program.get(programCounter);
        int opcode = getOpcode(currentValue);
        //Change to array eventually?
        int inputCounter = 1;
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
                if(inputCounter == 1){
                    result = input;
                } else {
                    result = secondInput;
                }
                inputCounter++;
                increaseBy = 2;
                int writeToPosition = program.get(programCounter + 1);
                program.set(writeToPosition, result);
            } else if(opcode == 4){
                //Output value of only 1 parameter.
                increaseBy = 2;

                if(getParameterMode(currentValue, 1) == ParameterModes.POSITION_MODE){  
                    output = program.get(program.get(programCounter + 1));
                } else {
                    output = program.get(programCounter + 1);
                }
            } else if(opcode == 5){
                //Jump if true
                int value1 = 0;
                if(getParameterMode(currentValue, 1) == ParameterModes.POSITION_MODE){
                    value1 = program.get(program.get(programCounter + 1));
                } else {
                    value1 = program.get(programCounter + 1);
                }

                if(value1 != 0){
                    int value2 = 0;
                    if(getParameterMode(currentValue, 0) == ParameterModes.POSITION_MODE){
                        value2 = program.get(program.get(programCounter + 2));
                    } else {
                        value2 = program.get(programCounter + 2);
                    }
                    programCounter = value2;
                    increaseBy = 0;
                } else {
                    increaseBy = 3;
                }
            } else if (opcode == 6){
                //Jump if false
                int value1 = 0;
                if(getParameterMode(currentValue, 1) == ParameterModes.POSITION_MODE){
                    value1 = program.get(program.get(programCounter + 1));
                } else {
                    value1 = program.get(programCounter + 1);
                }

                if(value1 == 0){
                    int value2 = 0;
                    if(getParameterMode(currentValue, 0) == ParameterModes.POSITION_MODE){
                        value2 = program.get(program.get(programCounter + 2));
                    } else {
                        value2 = program.get(programCounter + 2);
                    }
                    programCounter = value2;
                    increaseBy = 0;
                } else {
                    increaseBy = 3;
                }
            } else if (opcode == 7){
                //Less Than
                int param1 = getParameterMode(currentValue, 1) == ParameterModes.POSITION_MODE ? program.get(program.get(programCounter + 1)) : program.get(programCounter + 1);
                int param2 = getParameterMode(currentValue, 0) == ParameterModes.POSITION_MODE ? program.get(program.get(programCounter + 2)) : program.get(programCounter + 2);

                if(param1 < param2){
                    int writeToPosition = program.get(programCounter + 3);
                    program.set(writeToPosition, 1);
                } else {
                    int writeToPosition = program.get(programCounter + 3);
                    program.set(writeToPosition, 0);
                }
            } else if (opcode == 8){
                int param1 = getParameterMode(currentValue, 1) == ParameterModes.POSITION_MODE ? program.get(program.get(programCounter + 1)) : program.get(programCounter + 1);
                int param2 = getParameterMode(currentValue, 0) == ParameterModes.POSITION_MODE ? program.get(program.get(programCounter + 2)) : program.get(programCounter + 2);

                if(param1 == param2){
                    int writeToPosition = program.get(programCounter + 3);
                    program.set(writeToPosition, 1);
                } else {
                    int writeToPosition = program.get(programCounter + 3);
                    program.set(writeToPosition, 0);
                }
            }

            programCounter += increaseBy;
            currentValue = program.get(programCounter);
            opcode = getOpcode(currentValue);
        } while (opcode != 99);
    }

    public void run(int input){
        this.input = input;
        run();
    }

   public void setInput(int input){
       this.input = input;
   }

   public void setSecondInput(int input){
       secondInput = input;
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