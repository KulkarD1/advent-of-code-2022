package com.kulkard1.advent2022.day9;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Rope {
    private GridCoordinate headPosition;

    private GridCoordinate tailPosition;

    private Set<GridCoordinate> TAIL_VISITS = new HashSet<>();

    public Rope() {
        headPosition = new GridCoordinate(5, 1);
        tailPosition = new GridCoordinate(5, 1);
        TAIL_VISITS.add(new GridCoordinate(5, 1));
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
            if(Math.abs(tailPosition.getColumn() - headPosition.getColumn()) == 2 && tailPosition.getRow().equals(headPosition.getRow())) {
                int step = tailPosition.getColumn() > headPosition.getColumn() ? -1 : 1;
                tailPosition.setColumn(tailPosition.getColumn() + step);
                TAIL_VISITS.add(new GridCoordinate(tailPosition.getRow(), tailPosition.getColumn()));
            } else if(Math.abs(tailPosition.getRow() - headPosition.getRow()) == 2 && tailPosition.getColumn().equals(headPosition.getColumn())) {
                int step = tailPosition.getRow() > headPosition.getRow() ? -1 : 1;
                tailPosition.setRow(tailPosition.getRow() + step);
                TAIL_VISITS.add(new GridCoordinate(tailPosition.getRow(), tailPosition.getColumn()));
            } else if(Math.abs(tailPosition.getColumn() - headPosition.getColumn()) == 2) {
                //diagonal
                tailPosition.setRow(headPosition.getRow());
                int step = tailPosition.getColumn() > headPosition.getColumn() ? -1 : 1;
                tailPosition.setColumn(tailPosition.getColumn() + step);
                TAIL_VISITS.add(new GridCoordinate(tailPosition.getRow(), tailPosition.getColumn()));
            } else if(Math.abs(tailPosition.getRow() - headPosition.getRow()) == 2) {
                //diagonal
                tailPosition.setColumn(headPosition.getColumn());
                int step = tailPosition.getRow() > headPosition.getRow() ? -1 : 1;
                tailPosition.setRow(tailPosition.getRow() + step);
                TAIL_VISITS.add(new GridCoordinate(tailPosition.getRow(), tailPosition.getColumn()));
            }
            move(direction, steps - 1);
        }
        return this;
    }


}
