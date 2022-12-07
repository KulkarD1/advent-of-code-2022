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

            final File fileSystemRoot = linesStream
                                            .map(Command::new)
                                            .reduce(new File(true, "/", -1, null),
                                                    (currentFileSystem, command) -> command.execute(currentFileSystem),
                                                    (fileSystem1, fileSystem2) -> fileSystem1.getRoot())
                                            .getRoot();
            long availableSpace = 70000000L - fileSystemRoot.getRoot().getSize();
            long requiredFreeUp = 30000000L - availableSpace;
            final Long requiredSize = fileSystemRoot
                    .getDirectories()
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
