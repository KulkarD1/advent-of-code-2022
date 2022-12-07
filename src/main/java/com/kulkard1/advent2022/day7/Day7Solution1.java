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
            File fileSystem = new File(true, "/", -1, null);
            final File reduce = linesStream
                                    .map(Command::new)
                                    .reduce(fileSystem, (currentFileSystem, command) -> command.execute(currentFileSystem), (a, b) -> a);
            assertEquals(1845346, fileSystem.getDirectories().stream().mapToLong(File::getSize).filter(size -> size <= 100000).sum());
        }
    }
}
