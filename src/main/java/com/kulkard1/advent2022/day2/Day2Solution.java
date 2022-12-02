package com.kulkard1.advent2022.day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day2Solution {
    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-2-input").toURI()))) {
            final int totalScore = linesStream
                                    .map(RockPaperScissors::new)
                                    .map(RockPaperScissors::getScore)
                                    .mapToInt(i -> i)
                                    .sum();
            System.out.println("totalScore = " + totalScore);
        }
    }
}
