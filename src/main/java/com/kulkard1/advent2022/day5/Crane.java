package com.kulkard1.advent2022.day5;

import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

public class Crane {
    private final Integer howMany;

    private final Integer fromStackIndex;

    private final Integer toStackIndex;

    public Crane(String instruction) {
        this.howMany = Integer.valueOf(instruction.substring(instruction.indexOf("move ") + 5, instruction.indexOf("from")).trim());
        this.fromStackIndex = Integer.valueOf(instruction.substring(instruction.indexOf("from ") + 5, instruction.indexOf("to")).trim());
        this.toStackIndex = Integer.valueOf(instruction.substring(instruction.indexOf("to ") + 3));
    }

    public void execute(Map<Integer, Stack<String>> stacksMap) {
        Stack<String> fromStack = stacksMap.get(fromStackIndex);
        Stack<String> toStack = stacksMap.get(toStackIndex);
        Stack<String> containerStackToMove = new Stack<>();
        IntStream.rangeClosed(1, howMany)
            .forEach(index -> containerStackToMove.push(fromStack.pop()));
        IntStream.rangeClosed(1, howMany)
                .forEach(index -> toStack.push(containerStackToMove.pop()));
    }
}
