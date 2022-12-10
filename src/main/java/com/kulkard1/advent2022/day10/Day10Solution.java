package com.kulkard1.advent2022.day10;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Solution {
    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-10-input").toURI()))) {

            Program program = new Program();
            linesStream.forEach(program::executeInstruction);
            final int cumulativeSignalStrength = program.getCumulativeSignalStrength();
            assertEquals(13760, cumulativeSignalStrength);
        }
    }
}
