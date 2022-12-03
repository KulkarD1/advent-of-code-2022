package com.kulkard1.advent2022.day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day3Solution1 {

    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-3-input").toURI()))) {
            final int sum = linesStream
                    .map(Rucksack::new)
                    .mapToInt(Rucksack::getCommonItemPriority)
                    .sum();
            System.out.println("sum = " + sum);
        }
    }
}
