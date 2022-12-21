package com.kulkard1.advent2022.day12;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Solution {
    public static void main(String[] args) throws URISyntaxException, IOException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-12-input").toURI()))) {
            AtomicReference<GridCoordinate> start = new AtomicReference<>();
            List<List<GridCoordinate>> grid = new ArrayList<>();
            linesStream.forEach(line -> {
                final List<GridCoordinate> row = IntStream.range(0, line.length())
                        .mapToObj(column -> new GridCoordinate((short) grid.size(), (short) column, (short) line.charAt(column)))
                        .peek(gridCoordinate -> {
                            if(gridCoordinate.getValue() == 83) {
                                start.set(gridCoordinate);
                            }
                        })
                        .collect(Collectors.toList());
                grid.add(row);
            });
            GridCoordinate endShortestDepth = search(start.get(), grid).orElseThrow();
            System.out.println(endShortestDepth);
            assertEquals(447, endShortestDepth.getDepth());
        }
    }

    public static Optional<GridCoordinate> search(GridCoordinate root, List<List<GridCoordinate>> grid) {
        Queue<GridCoordinate> queue = new PriorityQueue<>(1000000000, Comparator.comparing(GridCoordinate::getWeight).reversed());
        //Queue<GridCoordinate> queue = new ArrayDeque<>(1000000000);
        Optional<GridCoordinate> likelyCoordinate = Optional.empty();
        queue.add(root);
        GridCoordinate currentCoordinate;
        while(!queue.isEmpty()) {
            currentCoordinate = queue.remove();
            if(currentCoordinate.getValue() == 69) {
                return Optional.of(currentCoordinate);
            } else {
                currentCoordinate.setVisited(true);
                List<GridCoordinate> possiblePaths = currentCoordinate.getPossiblePaths(grid);
                short currentDepth = currentCoordinate.getDepth();
                possiblePaths.forEach(gridCoordinate -> {
                    if(gridCoordinate.getDepth() == 0 || gridCoordinate.getDepth() > currentDepth + 1) {
                        queue.remove(gridCoordinate);
                        gridCoordinate.setDepth((short) (currentDepth + 1));
                        queue.add(gridCoordinate);
                    }
                });
            }
        }
        return likelyCoordinate;
    }
}
