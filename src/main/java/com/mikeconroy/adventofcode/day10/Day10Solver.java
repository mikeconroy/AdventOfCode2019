package com.mikeconroy.adventofcode.day10;

import java.util.ArrayList;

import com.mikeconroy.adventofcode.InputReader;
import com.mikeconroy.adventofcode.Solver;

public class Day10Solver implements Solver {

    private String fileLocation = "inputs/day10";
    private char[][] asteroidMap;
    private ArrayList<Asteroid> asteroidsList = new ArrayList<>();

    public static void main(String[] args){
        Day10Solver solver = new Day10Solver();
        System.out.println(solver.solve(1));
    }

    @Override
    public int solve(int part) {
        loadInput();
        if(part == 1){
            return calculatePart1();
        } else if(part == 2){
            // return calculatePart2();
        }
        return 0;
    }

    private int calculatePart1(){
        //Loop over each asteroid.
            //Loop over the other asteroids
            //Add the angle of each to the Set of Angles.
        //Return count of set from highest asteroid set.
        int maxCount = 0;
        for(Asteroid asteroid : asteroidsList){
            asteroid.populateLineOfSightSet(asteroidsList);
            if(asteroid.getLineOfSightCount() > maxCount){
                maxCount = asteroid.getLineOfSightCount();
            }
        }
        return maxCount;
    }

    private void loadInput(){
        String[] lines = InputReader.readFileLines(fileLocation);
        asteroidMap = new char[lines.length][lines[0].length()];
        for(int yIndex = 0; yIndex < lines.length; yIndex++){
            for(int xIndex = 0; xIndex < lines[yIndex].length(); xIndex++){
                asteroidMap[yIndex][xIndex] = lines[yIndex].charAt(xIndex);
                if(asteroidMap[yIndex][xIndex] == '#'){
                    asteroidsList.add(new Asteroid(xIndex, yIndex));
                }
            }
        }
    }

    private void printMap(){
        for(int yIndex = 0; yIndex < asteroidMap.length; yIndex++){
            for(int xIndex = 0; xIndex < asteroidMap.length; xIndex++){
                System.out.print(asteroidMap[yIndex][xIndex]);
            }
            System.out.println();
        }
    }
}