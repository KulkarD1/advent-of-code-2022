package com.kulkard1.advent2022.day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Day9Solution {

    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-9-input").toURI()))) {
            final int answer1 = linesStream
                    .reduce(new Rope(2), Rope::executeCommand, (rope1, rope2) -> rope1).getTAIL_VISITS().size();
            assertEquals(6354, answer1);
        }

        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-9-input").toURI()))) {
            final int answer2 = linesStream
                    .reduce(new Rope(10), Rope::executeCommand, (rope1, rope2) -> rope1).getTAIL_VISITS().size();
            assertNotEquals(2414, answer2); //too low
            assertEquals(2651, answer2); //too low
        }

    }
}
