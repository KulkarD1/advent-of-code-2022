package com.kulkard1.advent2022.day1;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class Day1Problem2 {
    public static void main(String... args) throws IOException, URISyntaxException {
        final int sumTop3 = Flux.fromStream(Files.lines(Paths.get(ClassLoader.getSystemResource("day-1-input").toURI())))
                .bufferUntil(String::isEmpty)
                .map(list -> list.stream().filter(line -> !line.isEmpty()).mapToInt(Integer::parseInt).sum())
                .toStream()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .mapToInt(i -> i)
                .sum();

        System.out.println("sumTop3 = " + sumTop3);
    }
}
