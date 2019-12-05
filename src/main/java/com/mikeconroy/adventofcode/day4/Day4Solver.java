package com.mikeconroy.adventofcode.day4;

import com.mikeconroy.adventofcode.InputReader;
import com.mikeconroy.adventofcode.Solver;

public class Day4Solver implements Solver {

    private String fileLocation = "inputs/day4";

    private int minimumValue;
    private int maximumValue;

    public int solve(int part){
        loadInput();

        if(part == 1){
            int count = 0;
            for(int value = minimumValue; value <= maximumValue; value++){
                if(containsTwoAdjacentDigits(value) && neverDecreases(value)){
                    count++;
                }
            }
            return count;
        } else if (part == 2) {
            int count = 0;
            for(int value = minimumValue; value <= maximumValue; value++){
                if(containsTwoAdjacentDigitsWithoutGroup(value) && neverDecreases(value)){
                    count++;
                }
            }
            return count;
        }
        return 0;
    }

    public boolean containsTwoAdjacentDigitsWithoutGroup(int value){
        String valueString = value + "";
        for(int index = 0; index < valueString.length() - 1; index++){
            if(valueString.charAt(index) == valueString.charAt(index+1)){
                if(index == 0){
                    if(valueString.charAt(index) != valueString.charAt(index + 2)){
                        return true;
                    }
                } else if(valueString.charAt(index) != valueString.charAt(index - 1)){
                    if(index == 4){
                        return true;
                    } else if (valueString.charAt(index) != valueString.charAt(index + 2)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean containsTwoAdjacentDigits(int value){
        String valueString = value + "";
        for(int index = 0; index < valueString.length() - 1; index++){
            if(valueString.charAt(index) == valueString.charAt(index+1)){
                return true;
            }
        }
        return false;
    }

    public boolean neverDecreases(int value){
        String valueAsString = value + "";
        int prevValue = 0;
        for(int index = 0; index < valueAsString.length(); index++){
            int currentValue = Integer.parseInt(valueAsString.charAt(index) + "");
            if(currentValue < prevValue){
                return false;
            }
            prevValue = currentValue;
        }
        return true;
    }
    

    private void loadInput(){
        String[] lines = InputReader.readFileLines(fileLocation);
        String[] range = lines[0].split("-");
        minimumValue = Integer.parseInt(range[0]);
        maximumValue = Integer.parseInt(range[1]);
    }

}