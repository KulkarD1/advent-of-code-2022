package com.kulkard1.advent2022.day9;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GridCoordinate {
    private Integer row;

    private Integer column;

    public void moveRight() {
        column++;
    }

    public void moveLeft() {
        column--;
    }

    public void moveUp() {
        row--;
    }

    public void moveDown() {
        row++;
    }

}
