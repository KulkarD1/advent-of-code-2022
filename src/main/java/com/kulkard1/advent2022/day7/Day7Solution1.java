package com.kulkard1.advent2022.day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Solution1 {

    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-7-input").toURI()))) {
            final long sum = linesStream
                .map(Command::new)
                .reduce(new File(true, "/", -1, null),
                        (currentFileSystem, command) -> command.execute(currentFileSystem),
                        (fileSystem1, fileSystem2) -> fileSystem1.getRoot())
                .getRoot()
                .getDirectories()
                .stream()
                .mapToLong(File::getSize)
                .filter(size -> size <= 100000)
                .sum();
            assertEquals(1845346, sum);
        }
    }
}
