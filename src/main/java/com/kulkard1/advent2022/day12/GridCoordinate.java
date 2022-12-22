package com.kulkard1.advent2022.day12;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

@Data
public class GridCoordinate {
    private final short row;

    private final short column;

    private final short value;

    @EqualsAndHashCode.Exclude
    private boolean visited;
    @EqualsAndHashCode.Exclude
    private short depth;

    public int getElevation() {
        return switch (value) {
            case 83 ->  97;
            case 69 ->  122;
            default -> value;
        };
    }

    public Optional<GridCoordinate> moveRight(List<List<GridCoordinate>> grid) {
        if(column + 1 < grid.get(row).size()) {
            GridCoordinate right = grid.get(row).get(column + 1);
            if(!right.isVisited() && this.getElevation() - right.getElevation() < 2) {
                return Optional.of(right);
            }
        }
        return Optional.empty();
    }

    public Optional<GridCoordinate> moveLeft(List<List<GridCoordinate>> grid) {
        if(column - 1 >= 0) {
            GridCoordinate left = grid.get(row).get(column - 1);
            if(!left.isVisited() && this.getElevation() - left.getElevation() < 2) {
                return Optional.of(left);
            }
        }
        return Optional.empty();
    }

    public Optional<GridCoordinate> moveUp(List<List<GridCoordinate>> grid) {
        if(row - 1 >= 0) {
            GridCoordinate up = grid.get(row - 1).get(column);
            if(!up.isVisited() && this.getElevation() - up.getElevation() < 2) {
                return Optional.of(up);
            }
        }
        return Optional.empty();
    }

    public Optional<GridCoordinate> moveDown(List<List<GridCoordinate>> grid) {
        if(row + 1 < grid.size()) {
            GridCoordinate down = grid.get(row + 1).get(column);
            if(!down.isVisited() && this.getElevation() - down.getElevation() < 2) {
                return Optional.of(down);
            }
        }
        return Optional.empty();
    }

    public List<GridCoordinate> getPossiblePaths(List<List<GridCoordinate>> grid) {
        List<GridCoordinate> possibleMoves = new ArrayList<>();
        Optional<GridCoordinate> right = moveRight(grid);
        Optional<GridCoordinate> left = moveLeft(grid);
        Optional<GridCoordinate> up = moveUp(grid);
        Optional<GridCoordinate> down = moveDown(grid);
        right.ifPresent(possibleMoves::add);
        left.ifPresent(possibleMoves::add);
        up.ifPresent(possibleMoves::add);
        down.ifPresent(possibleMoves::add);
        return possibleMoves;
    }
}