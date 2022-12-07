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
            File fileSystem = new File();
            fileSystem.setName("/");
            fileSystem.setDirectory(true);
            final File[] currentPointer = {fileSystem};
            linesStream.forEach(line-> {
                    if(line.startsWith("$ cd /")) {
                        //ignore
                    } else if (line.startsWith("$ cd ..")) {
                        currentPointer[0] = currentPointer[0].getParent();
                    } else if(line.startsWith("$ cd")) {
                        final String[] instructionMetadata = line.split(" ");
                        currentPointer[0] = currentPointer[0]
                                .getChildren()
                                .stream()
                                .filter(File::isDirectory)
                                .filter(directory -> directory.getName().equals(instructionMetadata[2]))
                                .findFirst()
                                .orElseThrow();

                    } else if (line.startsWith("$ ls")) {
                        //ignore
                    } else if (line.startsWith("dir")) {
                        final String[] fileMetadata = line.split(" ");
                        File file = new File();
                        file.setName(fileMetadata[1]);
                        file.setParent(currentPointer[0]);
                        file.setDirectory(true);
                        currentPointer[0].getChildren().add(file);
                    } else {
                        final String[] fileMetadata = line.split(" ");
                        File file = new File();
                        file.setName(fileMetadata[1]);
                        file.setSize(Long.parseLong(fileMetadata[0]));
                        file.setParent(currentPointer[0]);
                        file.setDirectory(false);
                        currentPointer[0].getChildren().add(file);
                    }
                }
            );
            assertEquals(1845346, fileSystem.getDirectories().stream().mapToLong(File::getSize).filter(size -> size <= 100000).sum());
        }
    }
}
