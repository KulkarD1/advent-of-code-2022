package com.kulkard1.advent2022.day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day9Solution1 {

    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-9-input").toURI()))) {
            final Rope reduce = linesStream
                    .reduce(new Rope(), Rope::executeCommand, (rope1, rope2) -> rope1);

            System.out.println("reduce.getTAIL_VISITS().size() = " + reduce.getTAIL_VISITS().size());
        }
    }
}
