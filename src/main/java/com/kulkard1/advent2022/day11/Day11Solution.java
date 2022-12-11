package com.kulkard1.advent2022.day11;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day11Solution {
    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-11-input").toURI()))) {
            Flux.fromStream(linesStream)
                    .bufferUntil(String::isBlank)
                    .map(Monkey::new)
                    .collectList()
                    .doOnNext(listMonkeys -> {
                        IntStream.rangeClosed(1, 20)
                            .forEach(index -> listMonkeys.forEach(monkey -> monkey.executeRound(listMonkeys)));
                        final Integer integer = listMonkeys
                                .stream()
                                .map(Monkey::getItemsInspected)
                                .sorted(Collections.reverseOrder())
                                .limit(2)
                                .reduce((i, j) -> i * j)
                                .orElseThrow();
                        System.out.println("integer = " + integer);
                    })
                    .subscribe();

        }
    }
}
