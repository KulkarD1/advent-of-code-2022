package com.kulkard1.advent2022.day12;

import lombok.Data;

import java.util.*;

@Data
public class GridCoordinate {
    private final short row;

    private final short column;

    private final short value;

    private boolean visited;

    private short depth;

    public int getWeight() {
        return row + column - depth;
    }

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
            if(!right.isVisited() && right.getElevation() - this.getElevation() < 2) {
                return Optional.of(right);
            }
        }
        return Optional.empty();
    }

    public Optional<GridCoordinate> moveLeft(List<List<GridCoordinate>> grid) {
        if(column - 1 >= 0) {
            GridCoordinate left = grid.get(row).get(column - 1);
            if(!left.isVisited() && left.getElevation() - this.getElevation() < 2) {
                return Optional.of(left);
            }
        }
        return Optional.empty();
    }

    public Optional<GridCoordinate> moveUp(List<List<GridCoordinate>> grid) {
        if(row - 1 >= 0) {
            GridCoordinate up = grid.get(row - 1).get(column);
            if(!up.isVisited() && up.getElevation() - this.getElevation() < 2) {
                return Optional.of(up);
            }
        }
        return Optional.empty();
    }

    public Optional<GridCoordinate> moveDown(List<List<GridCoordinate>> grid) {
        if(row + 1 < grid.size()) {
            GridCoordinate down = grid.get(row + 1).get(column);
            if(!down.isVisited() && down.getElevation() - this.getElevation() < 2) {
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
        possibleMoves.forEach(gridCoordinate -> gridCoordinate.setDepth((short) (this.getDepth() + 1)));
        return possibleMoves;
    }
}