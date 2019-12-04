package com.mikeconroy.adventofcode.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.mikeconroy.adventofcode.Solver;

public class Day1Solver implements Solver {

    private List<Integer> inputNumbers = new ArrayList<>();
    private String fileLocation = "inputs/day1";
    FuelCalculator fuelCalculator = new FuelCalculator();

    public int solve(int part){
        loadInput();
        if(part == 1){
            return calculateResultForPart1();
        } else if (part == 2) {
            return calculateResultForPart2();
        } else {
            return 0;
        }
    }

    private int calculateResultForPart1(){
        int result = 0;
        for (int moduleWeight : inputNumbers) {
            result += fuelCalculator.getFuelRequiredForModule(moduleWeight);
        }
        return result;
    }

    private int calculateResultForPart2(){
        int result = 0;
        for (int moduleWeight : inputNumbers) {
            result += getTotalFuelForModule(moduleWeight);
            // result += fuelCalculator.getFuelRequiredForModule(moduleWeight);
        }
        return result;
    }

    private int getTotalFuelForModule(int moduleWeight){
        int initialFuel = fuelCalculator.getFuelRequiredForModule(moduleWeight);
        if(initialFuel <= 0){
            return 0;
        } else {
            return initialFuel + getTotalFuelForModule(fuelCalculator.getFuelRequiredForModule(moduleWeight));
        }
    }

    private void loadInput(){
        BufferedReader bufferedReader;
        try{
            ClassLoader classLoader = new Day1Solver().getClass().getClassLoader();
            File inputFile = new File(classLoader.getResource(fileLocation).getFile());

            bufferedReader = new BufferedReader(new FileReader(inputFile)); 
        
            String line; 
            while ((line = bufferedReader.readLine()) != null) {
                inputNumbers.add(Integer.parseInt(line));
            }
            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}