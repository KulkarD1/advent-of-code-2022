package com.kulkard1.advent2022.day8;

import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
public class Tree {

    private final int rowIndex;
    private final int columnIndex;
    private final int height;

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

}
