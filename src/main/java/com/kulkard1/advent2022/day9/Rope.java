package com.kulkard1.advent2022.day9;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Data
public class Rope {
    private GridCoordinate headPosition;

    private List<GridCoordinate> tailPosition = new ArrayList<>();

    private Set<GridCoordinate> TAIL_VISITS = new HashSet<>();

    public Rope(int knots) {
        int row = 5000;
        int column = 1000;
        headPosition = new GridCoordinate(row, column);
        IntStream.range(1, knots).forEach(i -> tailPosition.add(new GridCoordinate(row, column)));
        TAIL_VISITS.add(new GridCoordinate(row, column));
    }

    public Rope executeCommand(String command) {
        final String[] commandMetadata = command.split(" ");
        String direction = commandMetadata[0];
        final Integer steps = Integer.valueOf(commandMetadata[1]);
        return move(direction, steps);
    }

    private Rope move(String direction, Integer steps) {
        if(steps > 0) {
            switch (direction) {
                case "R" -> headPosition.setColumn(headPosition.getColumn() + 1);
                case "L" -> headPosition.setColumn(headPosition.getColumn() - 1);
                case "U" -> headPosition.setRow(headPosition.getRow() - 1);
                case "D" -> headPosition.setRow(headPosition.getRow() + 1);
                default -> throw new UnsupportedOperationException();
            }
            moveTail(0);
            move(direction, steps - 1);
        }
        return this;
    }

    private void moveTail(int position) {
        GridCoordinate movedHeadPosition = position == 0 ? headPosition : tailPosition.get(position - 1);
        GridCoordinate tailPositionToMove = tailPosition.get(position);
        position++;
        if(Math.abs(tailPositionToMove.getColumn() - movedHeadPosition.getColumn()) == 2 && tailPositionToMove.getRow().equals(movedHeadPosition.getRow())) {
            int step = tailPositionToMove.getColumn() > movedHeadPosition.getColumn() ? -1 : 1;
            tailPositionToMove.setColumn(tailPositionToMove.getColumn() + step);
            if(position == tailPosition.size()) {
                TAIL_VISITS.add(new GridCoordinate(tailPositionToMove.getRow(), tailPositionToMove.getColumn()));
            }
        } else if(Math.abs(tailPositionToMove.getRow() - movedHeadPosition.getRow()) == 2 && tailPositionToMove.getColumn().equals(movedHeadPosition.getColumn())) {
            int step = tailPositionToMove.getRow() > movedHeadPosition.getRow() ? -1 : 1;
            tailPositionToMove.setRow(tailPositionToMove.getRow() + step);
            if(position == tailPosition.size()) {
                TAIL_VISITS.add(new GridCoordinate(tailPositionToMove.getRow(), tailPositionToMove.getColumn()));
            }
        } else if(Math.abs(tailPositionToMove.getColumn() - movedHeadPosition.getColumn()) == 2) {
            //diagonal
            tailPositionToMove.setRow(movedHeadPosition.getRow());
            int step = tailPositionToMove.getColumn() > movedHeadPosition.getColumn() ? -1 : 1;
            tailPositionToMove.setColumn(tailPositionToMove.getColumn() + step);
            if(position == tailPosition.size()) {
                TAIL_VISITS.add(new GridCoordinate(tailPositionToMove.getRow(), tailPositionToMove.getColumn()));
            }
        } else if(Math.abs(tailPositionToMove.getRow() - movedHeadPosition.getRow()) == 2) {
            //diagonal
            tailPositionToMove.setColumn(movedHeadPosition.getColumn());
            int step = tailPositionToMove.getRow() > movedHeadPosition.getRow() ? -1 : 1;
            tailPositionToMove.setRow(tailPositionToMove.getRow() + step);
            if(position == tailPosition.size()) {
                TAIL_VISITS.add(new GridCoordinate(tailPositionToMove.getRow(), tailPositionToMove.getColumn()));
            }
        }
        if(position < tailPosition.size()) {
            moveTail(position);
        }
    }

}
