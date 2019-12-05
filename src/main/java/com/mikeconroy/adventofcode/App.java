package com.mikeconroy.adventofcode;

import com.mikeconroy.adventofcode.day1.Day1Solver;
import com.mikeconroy.adventofcode.day2.Day2Solver;
import com.mikeconroy.adventofcode.day3.Day3Solver;
import com.mikeconroy.adventofcode.day4.Day4Solver;

public class App
{
    public static void main( String[] args )
    {
        Solver solver = new Day1Solver();
        System.out.println("Day 1: " + solver.solve(1));
        System.out.println("Day 1B: " + solver.solve(2));
        solver = new Day2Solver();
        System.out.println("Day 2: " + solver.solve(1));
        System.out.println("Day 2B: " + solver.solve(2));
        solver = new Day3Solver();
        System.out.println("Day 3: " + solver.solve(1));
        System.out.println("Day 3B: " + solver.solve(2));
        solver = new Day4Solver();
        System.out.println("Day 4: " + solver.solve(1));
        System.out.println("Day 4B: " + solver.solve(2));
    }
}
