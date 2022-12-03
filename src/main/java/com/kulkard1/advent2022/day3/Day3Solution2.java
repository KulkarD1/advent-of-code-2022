package com.kulkard1.advent2022.day3;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day3Solution2 {
    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-3-input").toURI()))) {
            final int sum = Flux.fromStream(linesStream)
                    .map(Rucksack::new)
                    .buffer(3)
                    .map(rucksacksByElfGroupList -> rucksacksByElfGroupList
                            .get(0)
                            .getAllItemsPriority()
                            .stream()
                            .distinct()
                            .filter(rucksacksByElfGroupList.get(1).getAllItemsPriority()::contains)
                            .filter(rucksacksByElfGroupList.get(2).getAllItemsPriority()::contains)
                            .findFirst()
                            .orElseThrow())
                    .toStream()
                    .mapToInt(i -> i)
                    .sum();
            System.out.println("sum = " + sum);
        }
    }
}
