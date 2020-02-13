package com.mikeconroy.adventofcode.day8;

import java.util.ArrayList;

import com.mikeconroy.adventofcode.InputReader;
import com.mikeconroy.adventofcode.Solver;

public class Day8Solver implements Solver {

    private String fileLocation = "inputs/day8";
    private String image;
    
    private int imageWidth = 25;
    private int imageHeight = 6;

    public int solve(int part){
        loadInput();
        if(part == 1){
            return calculatePart1();
        } else if(part == 2){
            return calculatePart2();
        }
        return 0;
    }

    private int calculatePart1(){
        ArrayList<ArrayList<Integer>> layers = parseLayers(imageWidth, imageHeight);
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

    private int calculatePart2(){
        ArrayList<ArrayList<Integer>> imageLayers = parseLayers(imageWidth, imageHeight);
        ArrayList<Integer> finalImage = new ArrayList<>();

        for(int pixel = 0; pixel < (imageWidth*imageHeight); pixel++){
            int currentLayer = 0;
            int currentPixel = imageLayers.get(currentLayer).get(pixel);
            while(currentPixel == 2){
                currentLayer++;
                currentPixel = imageLayers.get(currentLayer).get(pixel);
            }
            finalImage.add(currentPixel);
        }
        printImage(finalImage, imageWidth, imageHeight);
        return 0;
    }

    private void printImage(ArrayList<Integer> image, int width, int height){
        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                if(image.get((row * imageWidth) + col) == 0){
                    System.out.print(" ");
                } else if (image.get((row * imageWidth) + col) == 1){
                    System.out.print("#");
                }
            }
            System.out.println();
        }
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