package com.mikeconroy.adventofcode.day3;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mikeconroy.adventofcode.Solver;

public class Day3Solver implements Solver {

    private String fileLocation = "inputs/day3";

    private List<String> wire1Instructions;
    private List<String> wire2Instructions;

    public int solve(int part){
        loadInput();
        if (part == 1){
            return calculateResultForPart1();
        } else if (part == 2){
            return calculateResultForPart2();
        }
        return 0;
    }

    private int calculateResultForPart1(){
        List<Point> wire1Coordinates = new ArrayList<Point>();
        List<Point> wire2Coordinates = new ArrayList<Point>();

        addAllCoordinatesFromInstructions(wire1Coordinates, wire1Instructions);
        addAllCoordinatesFromInstructions(wire2Coordinates, wire2Instructions);

        List<Point> matchingCoords = getMatchingCoordinates(wire1Coordinates, wire2Coordinates);

        int distance = getShortestManhattanDistance(matchingCoords);

        return distance;
    }

    private int getShortestManhattanDistance(List<Point> coords){
        int shortestDistance = Integer.MAX_VALUE;
        for(Point coord : coords){
            if(getManhattanDistance(coord) < shortestDistance){
                shortestDistance = getManhattanDistance(coord);
            }
        }
        return shortestDistance;
    }

    private int getManhattanDistance(Point coordinate){
        return Math.abs(coordinate.x) + Math.abs(coordinate.y);
    }

    private List<Point> getMatchingCoordinates(List<Point> coordinatesOne, List<Point> coordinatesTwo){
        List<Point> matchingCoordinates = new ArrayList<>();
        for(Point coordinateOne : coordinatesOne){
            for(Point coordinateTwo : coordinatesTwo){
                if(coordinateOne.equals(coordinateTwo)){
                    matchingCoordinates.add(coordinateOne);
                }
            }
        }
        return matchingCoordinates;
    }

    private void addAllCoordinatesFromInstructions(List<Point> listOfCoordinates, List<String> instructions){
        Point currentPosition = new Point(0,0);
        for(int i = 0; i < instructions.size(); i++){
            currentPosition = applyInstruction(listOfCoordinates, currentPosition, instructions.get(i));
        }
    }

    private Point applyInstruction(List<Point> listOfCoords, Point currentPosition, String instruction){
        char direction = instruction.charAt(0);
        Point newPosition = new Point(currentPosition.x, currentPosition.y);

        int distance = Integer.parseInt(instruction.substring(1));

        for (int i = 0; i < distance; i++){
            switch(direction){
                case 'R':
                    newPosition.x = newPosition.x + 1;
                    break;
                case 'L':
                    newPosition.x = newPosition.x - 1;
                    break;
                case 'U':
                    newPosition.y = newPosition.y + 1;
                    break;
                case 'D':
                    newPosition.y = newPosition.y - 1;
                    break;
            }
            listOfCoords.add(new Point(newPosition));
        }

        return newPosition;
    }

    private int calculateResultForPart2(){
        return 0;
    }

    private void loadInput(){
        wire1Instructions = new ArrayList<String>();
        wire2Instructions = new ArrayList<String>();

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream stream = classLoader.getResourceAsStream(fileLocation);
        Scanner scanner = new Scanner(stream);

        String[] instructions = scanner.nextLine().split(",");
        for(String instruction : instructions){
            wire1Instructions.add(instruction);
        }

        instructions = scanner.nextLine().split(",");
        for (String instruction : instructions) {
            wire2Instructions.add(instruction);
        }

        try {
            stream.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}