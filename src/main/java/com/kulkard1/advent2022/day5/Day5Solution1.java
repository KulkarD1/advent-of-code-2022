package com.kulkard1.advent2022.day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day5Solution1 {

    private static final Map<Integer, Stack<String>> stacksMap = new HashMap<>();

    private static int craneInstructionIndex;

    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-5-input").toURI()))) {
            final List<String> inputLines = linesStream.toList();
            fillStacks(inputLines, 0);
            inputLines.stream()
                .skip(craneInstructionIndex)
                .map(Crane::new)
                .forEach(crane -> crane.execute(stacksMap));
        }
        final String collect = IntStream.rangeClosed(1, stacksMap.keySet().size())
                .mapToObj(stackId -> stacksMap.get(stackId).peek())
                .reduce("", (s1, s2) -> s1 + s2);
        System.out.println("collect = " + collect);
    }

    private static void fillStacks(List<String> inputLines, int readIndex) {
        if(inputLines.get(readIndex).contains("[")) {
            fillStacks(inputLines, readIndex + 1);
            final String[] containers = inputLines.get(readIndex).split("");
            IntStream.rangeClosed(1, stacksMap.keySet().size())
                .forEach(stackId -> {
                    int containerAtIndex = 1;
                    if(stackId > 1) {
                        containerAtIndex = containerAtIndex + (4 * (stackId - 1));
                    }
                    if(containerAtIndex < containers.length && !containers[containerAtIndex].trim().isEmpty()) {
                        stacksMap.get(stackId).push(containers[containerAtIndex].trim());
                    }
                });
        } else {
            final String[] stacksIds = inputLines.get(readIndex).trim().split("\s{2}");
            Arrays.stream(stacksIds)
                .forEach(stackId -> stacksMap.put(Integer.valueOf(stackId.trim()), new Stack<>()));
            craneInstructionIndex = readIndex + 2;
        }
    }
}
