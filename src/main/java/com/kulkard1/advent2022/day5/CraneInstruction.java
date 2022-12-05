package com.kulkard1.advent2022.day5;

import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CraneInstruction {
    private final Integer howMany;

    private final Integer fromStackIndex;

    private final Integer toStackIndex;

    public CraneInstruction(String instruction) {
        final Pattern digits = Pattern.compile("\\d+");
        final Matcher matcher = digits.matcher(instruction);
        assertTrue(matcher.find());
        this.howMany = Integer.valueOf(matcher.group());
        assertTrue(matcher.find());
        this.fromStackIndex = Integer.valueOf(matcher.group());
        assertTrue(matcher.find());
        this.toStackIndex =Integer.valueOf(matcher.group());
    }

    public void execute(Map<Integer, Stack<String>> stacksMap) {
        Stack<String> fromStack = stacksMap.get(fromStackIndex);
        Stack<String> toStack = stacksMap.get(toStackIndex);
        Stack<String> containerStackToMove = new Stack<>();
        IntStream.rangeClosed(1, howMany)
            .forEach(index -> containerStackToMove.push(fromStack.pop()));
        IntStream.rangeClosed(1, containerStackToMove.size())
                .forEach(index -> toStack.push(containerStackToMove.pop()));
    }
}
