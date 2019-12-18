package com.mikeconroy.adventofcode.day6;

import com.mikeconroy.adventofcode.InputReader;
import com.mikeconroy.adventofcode.Solver;

public class Day6Solver implements Solver {
    
    private String fileLocation = "inputs/day6";

    Graph orbits = new Graph();

    public int solve(int part){
        loadInput();
        if(part == 1){
            return orbits.countTotalOrbits();
        } else if(part == 2){
            return orbits.getOrbitalTransfersRequired("YOU", "SAN");
        }
        return 0;
    }

    private void loadInput(){
        String[] lines = InputReader.readFileLines(fileLocation);

        for(String orbit : lines){
            String[] orbitParts = orbit.split("\\)");
            orbits.put(orbitParts[0], orbitParts[1]);
        }
    }

}