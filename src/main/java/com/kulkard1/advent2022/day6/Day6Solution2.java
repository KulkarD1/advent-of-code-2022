package com.kulkard1.advent2022.day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Solution2 {

    public static void main(String... args) throws IOException, URISyntaxException {
        final int block = 14;
        final String input = Files.readString(Paths.get(ClassLoader.getSystemResource("day-6-input").toURI()));
        final int first = IntStream.range(0, input.length())
                .filter(index -> Arrays.stream(input.substring(index, index + block).split("")).collect(Collectors.toSet()).size() == block)
                .map(i -> i + block)
                .findFirst()
                .orElseThrow();
        System.out.println("first = " + first);
        assertEquals(2564, first);
    }
}
