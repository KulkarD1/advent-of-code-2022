package com.kulkard1.advent2022.day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Solution1 {

    public static void main(String... args) throws IOException, URISyntaxException {
        final String input = Files.readString(Paths.get(ClassLoader.getSystemResource("day-6-input").toURI()));
        final int first = IntStream.range(0, input.length())
                .filter(index -> {
                    final String substring = input.substring(index, index + 4);
                    return Arrays.stream(substring.split("")).collect(Collectors.toSet()).size() == 4;
                })
                .findFirst()
                .orElseThrow() + 4;
        assertEquals(1356, first);
    }
}
