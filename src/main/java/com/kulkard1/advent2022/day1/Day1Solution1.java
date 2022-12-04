package com.kulkard1.advent2022.day1;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day1Solution1 {

    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-1-input").toURI()))) {
            Flux.fromStream(linesStream)
                    .bufferUntil(String::isEmpty)
                    .map(caloriesPerElfList -> caloriesPerElfList.stream().filter(line -> !line.isEmpty()).mapToInt(Integer::parseInt).sum())
                    .reduce(0, Integer::max)
                    .doOnNext(System.out::println)
                    .subscribe();
        }

    }
}
