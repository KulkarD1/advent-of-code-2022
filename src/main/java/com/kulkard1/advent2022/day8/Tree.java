package com.kulkard1.advent2022.day8;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public record Tree(int rowIndex, int columnIndex, int height) {

    public boolean isOnTheEdge(List<List<Integer>> grid) {
        return columnIndex == 0 || columnIndex == (grid.size() - 1) || rowIndex == 0 || rowIndex == (grid.get(rowIndex).size() - 1);
    }

    public boolean isVisibleFromTheInterior(List<List<Integer>> grid) {
        final boolean leftVisibility = IntStream.range(0, columnIndex)
                .map(columnIndexToCompare -> grid.get(rowIndex).get(columnIndexToCompare))
                .allMatch(heightToCompare -> heightToCompare < height);

        final boolean rightVisibility = IntStream.range(columnIndex + 1, grid.get(rowIndex).size())
                .map(columnIndexToCompare -> grid.get(rowIndex).get(columnIndexToCompare))
                .allMatch(heightToCompare -> heightToCompare < height);

        final boolean topVisibility = IntStream.range(0, rowIndex)
                .map(rowIndexToCompare -> grid.get(rowIndexToCompare).get(columnIndex))
                .allMatch(heightToCompare -> heightToCompare < height);

        final boolean bottomVisibility = IntStream.range(rowIndex + 1, grid.size())
                .map(rowIndexToCompare -> grid.get(rowIndexToCompare).get(columnIndex))
                .allMatch(heightToCompare -> heightToCompare < height);

        return leftVisibility || rightVisibility || topVisibility || bottomVisibility;
    }

    public Long getScenicScore(List<List<Integer>> grid) {
        long leftScenicScore = IntStream.range(0, columnIndex)
                .boxed()
                .sorted(Collections.reverseOrder())
                .map(columnIndexToCompare -> grid.get(rowIndex).get(columnIndexToCompare))
                .takeWhile(heightToCompare -> heightToCompare < height)
                .count();
        if(leftScenicScore < columnIndex) {
            leftScenicScore++;
        }

        long rightScenicScore = IntStream.range(columnIndex + 1, grid.get(rowIndex).size())
                .map(columnIndexToCompare -> grid.get(rowIndex).get(columnIndexToCompare))
                .takeWhile(heightToCompare -> heightToCompare < height)
                .count();
        if(rightScenicScore < (grid.get(rowIndex).size() - (columnIndex + 1))) {
            rightScenicScore++;
        }

        long topScenicScore = IntStream.range(0, rowIndex)
                .boxed()
                .sorted(Collections.reverseOrder())
                .map(rowIndexToCompare -> grid.get(rowIndexToCompare).get(columnIndex))
                .takeWhile(heightToCompare -> heightToCompare < height)
                .count();
        if(topScenicScore < rowIndex) {
            topScenicScore++;
        }

        long bottomScenicScore = IntStream.range(rowIndex + 1, grid.size())
                .map(rowIndexToCompare -> grid.get(rowIndexToCompare).get(columnIndex))
                .takeWhile(heightToCompare -> heightToCompare < height)
                .count();
        if(bottomScenicScore < (grid.size() - (rowIndex + 1))) {
            bottomScenicScore++;
        }
        return leftScenicScore * rightScenicScore * topScenicScore * bottomScenicScore;
    }

}
