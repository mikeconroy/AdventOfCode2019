package com.mikeconroy.adventofcode;

import com.mikeconroy.adventofcode.day1.Day1Solver;
import com.mikeconroy.adventofcode.day2.Day2Solver;

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
    }
}
