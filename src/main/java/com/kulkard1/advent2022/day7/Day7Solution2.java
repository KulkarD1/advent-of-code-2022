package com.kulkard1.advent2022.day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Solution2 {

    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-7-input").toURI()))) {
            File fileSystem = new File(true, "/", -1, null);
            final File reduce = linesStream
                    .map(Command::new)
                    .reduce(fileSystem, (currentFileSystem, command) -> command.execute(currentFileSystem), (a, b) -> a);
            long availableSpace = 70000000L - fileSystem.getSize();
            long requiredFreeUp = 30000000L - availableSpace;
            final Long requiredSize = fileSystem.getDirectories()
                    .stream()
                    .sorted(Comparator.comparing(File::getSize))
                    .map(File::getSize)
                    .filter(size -> size > requiredFreeUp)
                    .findFirst()
                    .orElseThrow();
            assertEquals(3636703, requiredSize);
        }

    }
}
