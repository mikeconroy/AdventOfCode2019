package com.mikeconroy.adventofcode.day3;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
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
        List<PointWithSteps> wire1Coordinates = new ArrayList<>();
        List<PointWithSteps> wire2Coordinates = new ArrayList<>();

        addAllCoordinatesFromInstructions(wire1Coordinates, wire1Instructions);
        addAllCoordinatesFromInstructions(wire2Coordinates, wire2Instructions);

        List<PointWithSteps> matchingCoords = getMatchingCoordinates(wire1Coordinates, wire2Coordinates);
        int distance = getShortestManhattanDistance(matchingCoords);

        return distance;
    }

    private int calculateResultForPart2(){
        List<PointWithSteps> wire1Coordinates = new ArrayList<>();
        List<PointWithSteps> wire2Coordinates = new ArrayList<>();

        addAllCoordinatesFromInstructions(wire1Coordinates, wire1Instructions);
        addAllCoordinatesFromInstructions(wire2Coordinates, wire2Instructions);

        List<PointWithSteps> matchingCoords = getMatchingCoordinates(wire1Coordinates, wire2Coordinates);
        int leastSteps = Integer.MAX_VALUE;

        for(PointWithSteps intersection : matchingCoords){
            if(intersection.steps < leastSteps){
                leastSteps = intersection.steps;
            }
        }
        return leastSteps;
    }

    private int getShortestManhattanDistance(List<PointWithSteps> coords){
        int shortestDistance = Integer.MAX_VALUE;
        for(PointWithSteps coord : coords){
            if(getManhattanDistance(coord.location) < shortestDistance){
                shortestDistance = getManhattanDistance(coord.location);
            }
        }
        return shortestDistance;
    }

    private int getManhattanDistance(Point coordinate){
        return Math.abs(coordinate.x) + Math.abs(coordinate.y);
    }

    private List<PointWithSteps> getMatchingCoordinates(List<PointWithSteps> coordinatesOne, List<PointWithSteps> coordinatesTwo){

        //Convert the Lists to Sets to make it quicker to search for matches.
        //With 2 Lists - 2 nested loops are required. This is very slow over a large number of coordinates.

        HashSet<Point> coordinateSetOne = new HashSet<>();
        for (PointWithSteps coord : coordinatesOne){
            coordinateSetOne.add(coord.location);
        }

        HashSet<Point> coordinateSetTwo = new HashSet<>();
        for (PointWithSteps coord : coordinatesTwo){
            coordinateSetTwo.add(coord.location);
        }

        List<Point> matchingCoords = new ArrayList<>();
        for(Point wire1Coord : coordinateSetOne){
            if(coordinateSetTwo.contains(wire1Coord)){
                matchingCoords.add(wire1Coord);
            }
        }

        List<PointWithSteps> matchingCoordinatesWithStepsList = new ArrayList<>();
        for (Point intersection : matchingCoords){
            int totalSteps = 0;
            for(int coordinate1Location = 0; coordinate1Location < coordinatesOne.size(); coordinate1Location++){
                if(coordinatesOne.get(coordinate1Location).location.equals(intersection)){
                    totalSteps += coordinate1Location + 1;
                    break;
                }
            }

            for(int coordinate2Location = 0; coordinate2Location < coordinatesTwo.size(); coordinate2Location++){
                if(coordinatesTwo.get(coordinate2Location).location.equals(intersection)){
                    totalSteps += coordinate2Location + 1;
                }
            }
            matchingCoordinatesWithStepsList.add(new PointWithSteps(intersection, totalSteps));

        }

        return matchingCoordinatesWithStepsList;
    }

    private void addAllCoordinatesFromInstructions(List<PointWithSteps> listOfCoordinates, List<String> instructions){
        Point currentPosition = new Point(0,0);
        int currentDistance = 0;
        for(int i = 0; i < instructions.size(); i++){
            currentPosition = applyInstruction(listOfCoordinates, currentPosition, currentDistance, instructions.get(i));
            currentDistance = listOfCoordinates.size();
        }
    }

    private Point applyInstruction(List<PointWithSteps> listOfCoords, Point currentPosition, int currentDistance, String instruction){
        char direction = instruction.charAt(0);
        Point newPosition = new Point(currentPosition.x, currentPosition.y);
        int newDistance = currentDistance;
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
            newDistance = newDistance + 1;
            listOfCoords.add(new PointWithSteps(newPosition.getLocation(), newDistance));
        }

        return newPosition;
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