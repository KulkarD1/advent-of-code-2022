package com.kulkard1.advent2022.day10;

import lombok.Data;

@Data
public class Program {

    private int x = 1;

    private int cycle;

    private int cumulativeSignalStrength;

    public void executeInstruction(String instruction) {
        if(instruction.equals("noop")) {
            executeCycle();
        } else {
            final String[] instructionMetadata = instruction.split(" ");
            executeCycle();
            executeCycle();
            x += Integer.parseInt(instructionMetadata[1]);
        }
    }

    private void executeCycle() {
        cycle++;
        cumulativeSignalStrength += switch (cycle) {
            case 20, 60, 100, 140, 180, 220 -> cycle * x;
            default -> 0;
        };
    }
}
