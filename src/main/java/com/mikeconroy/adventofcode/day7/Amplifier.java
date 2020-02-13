package com.mikeconroy.adventofcode.day7;

import java.util.List;

import com.mikeconroy.adventofcode.day2.IntCodeComputer;

public class Amplifier {

    List<Integer> program;
    IntCodeComputer computer = new IntCodeComputer();

    public Amplifier(List<Integer> program){
        this.program = program;
        computer.loadProgram(program);
    }

    public void setPhaseSetting(int phaseSetting){
        //set the phase setting on first instruction.
        computer.setInput(phaseSetting);
    }

    public void setInputSignal(int inputSignal){
        computer.setSecondInput(inputSignal);
    }

    public int getOutput(){
        computer.loadProgram(program);
        computer.run();
        program = computer.getProgram();
        return computer.getOutput();
    }

    public int getOutputWithoutReload(){
        computer.run();
        return computer.getOutput();
    }

}