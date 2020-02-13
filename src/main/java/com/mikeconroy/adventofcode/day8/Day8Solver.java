package com.mikeconroy.adventofcode.day8;

import java.util.ArrayList;

import com.mikeconroy.adventofcode.InputReader;
import com.mikeconroy.adventofcode.Solver;

public class Day8Solver implements Solver {

    private String fileLocation = "inputs/day8";
    private String image;

    public int solve(int part){
        loadInput();
        if(part == 1){
            return calculatePart1();
        } else if(part == 2){
            return 0;
        }
        return 0;
    }

    private int calculatePart1(){
        ArrayList<ArrayList<Integer>> layers = parseLayers(25, 6);
        int leastZeroes = Integer.MAX_VALUE;
        int layerNum = -1;
        for(int index = 0; index < layers.size(); index++){
            int numOfZeroes = countDigitInLayer(layers.get(index), 0);
            if(numOfZeroes < leastZeroes){
                leastZeroes = numOfZeroes;
                layerNum = index;
            }
        }
        int numOfOnes = countDigitInLayer(layers.get(layerNum), 1);
        int numOfTwos = countDigitInLayer(layers.get(layerNum), 2);
        return numOfOnes * numOfTwos;
    }

    private int countDigitInLayer(ArrayList<Integer> layer, int countDigit){
        int digitCount = 0;
        for(int num : layer){
            if(num == countDigit){
                digitCount++;
            }
        }
        return digitCount;
    }

    private ArrayList<ArrayList<Integer>> parseLayers(int width, int height){
        ArrayList<ArrayList<Integer>> layers = new ArrayList<>();
        int pixelsPerLayer = width * height;
        for(int i = 0; i < image.length(); i += pixelsPerLayer){
            ArrayList<Integer> layer = new ArrayList<>();
            for(int j = 0; j < pixelsPerLayer; j++){
                layer.add(Integer.parseInt("" + image.charAt(i+j)));
            }
            layers.add(layer);
        }
        return layers;
    }

    private void loadInput(){
        String[] lines = InputReader.readFileLines(fileLocation);
        image = lines[0];
    }
}