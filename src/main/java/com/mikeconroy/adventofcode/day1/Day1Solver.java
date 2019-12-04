package com.mikeconroy.adventofcode.day1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mikeconroy.adventofcode.Solver;

public class Day1Solver implements Solver {

    private List<Integer> inputNumbers = new ArrayList<>();
    private String fileLocation = "inputs/day1";
    FuelCalculator fuelCalculator = new FuelCalculator();

    public int solve(int part) {
        loadInput();
        if (part == 1) {
            return calculateResultForPart1();
        } else if (part == 2) {
            return calculateResultForPart2();
        } else {
            return 0;
        }
    }

    private int calculateResultForPart1() {
        int result = 0;
        for (int moduleWeight : inputNumbers) {
            result += fuelCalculator.getFuelRequiredForModule(moduleWeight);
        }
        return result;
    }

    private int calculateResultForPart2() {
        int result = 0;
        for (int moduleWeight : inputNumbers) {
            result += getTotalFuelForModule(moduleWeight);
        }
        return result;
    }

    private int getTotalFuelForModule(int moduleWeight) {
        int initialFuel = fuelCalculator.getFuelRequiredForModule(moduleWeight);
        if (initialFuel <= 0) {
            return 0;
        } else {
            return initialFuel + getTotalFuelForModule(fuelCalculator.getFuelRequiredForModule(moduleWeight));
        }
    }

    private void loadInput() {
        inputNumbers = new ArrayList<Integer>();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream stream = classLoader.getResourceAsStream(fileLocation);
        Scanner scanner = new Scanner(stream);

        // System.out.println("" + scanner.next());
        while (scanner.hasNext()) {
            inputNumbers.add(Integer.parseInt(scanner.nextLine()));
        }

        try {
            stream.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}