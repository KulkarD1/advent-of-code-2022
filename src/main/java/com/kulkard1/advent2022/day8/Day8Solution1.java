package com.kulkard1.advent2022.day8;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Solution1 {
    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-8-input").toURI()))) {
            final List<List<Integer>> grid = linesStream
                    .map(line -> Arrays.stream(line.split("")).mapToInt(Integer::valueOf).boxed().toList())
                    .toList();

            Flux.range(0, grid.size())
                .flatMap(index -> Flux.combineLatest(arr -> new Tree((Integer) arr[0], (Integer) arr[1], grid.get((Integer) arr[0]).get((Integer) arr[1])), Mono.just(index), Flux.range(0, grid.get(index).size())))
                .filter(tree -> tree.isOnTheEdge(grid) || tree.isVisibleFromTheInterior(grid))
                .count()
                .doOnNext(count -> assertEquals(1676, count))
                .subscribe();
        }
    }
}
