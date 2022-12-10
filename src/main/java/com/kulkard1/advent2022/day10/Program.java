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
        if(cycle == 40 || cycle == 80 || cycle == 120 || cycle == 160 || cycle == 200 || cycle == 240) {
            System.out.println();
        }
        int spriteMiddlePosition = x;
        if(cycle > 40 && cycle < 80) {
            spriteMiddlePosition = x + 40;
        } else if (cycle > 80 && cycle < 120) {
            spriteMiddlePosition = x + 80;
        } else if (cycle > 120 && cycle < 160) {
            spriteMiddlePosition = x + 120;
        } else if (cycle > 160 && cycle < 200) {
            spriteMiddlePosition = x + 160;
        } else if (cycle > 200 && cycle < 240) {
            spriteMiddlePosition = x + 200;
        }
        if(cycle == spriteMiddlePosition || cycle == spriteMiddlePosition - 1 || cycle == spriteMiddlePosition + 1) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
        cycle++;
        cumulativeSignalStrength += switch (cycle) {
            case 20, 60, 100, 140, 180, 220 -> cycle * x;
            default -> 0;
        };
    }
}
