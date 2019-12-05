package com.mikeconroy.adventofcode.day4;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Day4SolverTest {

    @Test
    public void neverDecreasesTest(){
        Day4Solver solver = new Day4Solver();
        assertTrue(solver.neverDecreases(123456));
        assertTrue(solver.neverDecreases(111111));
        assertTrue(solver.neverDecreases(111145));

        assertFalse(solver.neverDecreases(111451));
        assertFalse(solver.neverDecreases(123454));
        assertFalse(solver.neverDecreases(654321));
        assertFalse(solver.neverDecreases(123245));
        assertFalse(solver.neverDecreases(111541));
    }

    @Test
    public void containsTwoAdjacentDigitsTest(){
        Day4Solver solver = new Day4Solver();
        assertTrue(solver.containsTwoAdjacentDigits(112345));
        assertTrue(solver.containsTwoAdjacentDigits(122345));
        assertTrue(solver.containsTwoAdjacentDigits(123345));
        assertTrue(solver.containsTwoAdjacentDigits(123455));
        assertTrue(solver.containsTwoAdjacentDigits(111223));
        assertTrue(solver.containsTwoAdjacentDigits(112333));

        assertTrue(solver.containsTwoAdjacentDigits(111123));
        assertTrue(solver.containsTwoAdjacentDigits(111123));
        assertTrue(solver.containsTwoAdjacentDigits(111111));
        assertTrue(solver.containsTwoAdjacentDigits(111345));
        assertTrue(solver.containsTwoAdjacentDigits(111145));
        assertTrue(solver.containsTwoAdjacentDigits(123555));
        assertTrue(solver.containsTwoAdjacentDigits(111123));
        assertTrue(solver.containsTwoAdjacentDigits(579999));
        assertTrue(solver.containsTwoAdjacentDigits(577777));

        assertFalse(solver.containsTwoAdjacentDigits(123456));
    }

    @Test
    public void containsTwoAdjacentDigitsWithoutGroup(){
        Day4Solver solver = new Day4Solver();
        assertTrue(solver.containsTwoAdjacentDigitsWithoutGroup(112345));
        assertTrue(solver.containsTwoAdjacentDigitsWithoutGroup(122345));
        assertTrue(solver.containsTwoAdjacentDigitsWithoutGroup(123345));
        assertTrue(solver.containsTwoAdjacentDigitsWithoutGroup(123455));
        assertTrue(solver.containsTwoAdjacentDigitsWithoutGroup(111223));
        assertTrue(solver.containsTwoAdjacentDigitsWithoutGroup(112333));

        assertFalse(solver.containsTwoAdjacentDigitsWithoutGroup(111123));
        assertFalse(solver.containsTwoAdjacentDigitsWithoutGroup(111123));
        assertFalse(solver.containsTwoAdjacentDigitsWithoutGroup(111111));
        assertFalse(solver.containsTwoAdjacentDigitsWithoutGroup(111345));
        assertFalse(solver.containsTwoAdjacentDigitsWithoutGroup(111145));
        assertFalse(solver.containsTwoAdjacentDigitsWithoutGroup(123555));
        assertFalse(solver.containsTwoAdjacentDigitsWithoutGroup(111123));
        assertFalse(solver.containsTwoAdjacentDigitsWithoutGroup(579999));
        assertFalse(solver.containsTwoAdjacentDigitsWithoutGroup(577777));
        assertFalse(solver.containsTwoAdjacentDigitsWithoutGroup(123456));
    }

}