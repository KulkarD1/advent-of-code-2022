package com.kulkard1.advent2022.day3;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Day3Solution2 {
    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-3-input").toURI()))) {
            final int sum = Flux.fromStream(linesStream)
                    .map(Rucksack::new)
                    .buffer(3)
                    .map(rucksacksByElfGroupList -> {
                        final List<Integer> allItemsPriority0 = rucksacksByElfGroupList.get(0).getAllItemsPriority();
                        final List<Integer> allItemsPriority1 = rucksacksByElfGroupList.get(1).getAllItemsPriority();
                        final List<Integer> allItemsPriority2 = rucksacksByElfGroupList.get(2).getAllItemsPriority();
                        return allItemsPriority0
                                .stream()
                                .distinct()
                                .filter(allItemsPriority1::contains)
                                .filter(allItemsPriority2::contains)
                                .findFirst()
                                .orElseThrow();
                    })
                    .toStream()
                    .mapToInt(i -> i)
                    .sum();
            System.out.println("sum = " + sum);
        }
    }
}
