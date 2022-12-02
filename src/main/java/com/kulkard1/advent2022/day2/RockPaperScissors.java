package com.kulkard1.advent2022.day2;

public class RockPaperScissors {

    String opponentMove;

    String move;

    public RockPaperScissors(String line) {
        String[] moves = line.split(" ");
        this.opponentMove = moves[0];
        this.move = moves[1];
    }

    public int getScore() {
        return getMovePoints() + getResultPoints();
    }

    private int getMovePoints() {
        return switch (this.move) {
            case "X" -> 1;
            case "Y" -> 2;
            case "Z" -> 3;
            default -> throw new RuntimeException("Unknown");
        };
    }

    private int getResultPoints() {
        return switch (this.opponentMove) {
            case "A" -> switch(this.move) {
                case "X" -> 3;
                case "Y" -> 6;
                case "Z" -> 0;
                default -> throw new RuntimeException("Unknown");
            };
            case "B" -> switch(this.move) {
                case "X" -> 0;
                case "Y" -> 3;
                case "Z" -> 6;
                default -> throw new RuntimeException("Unknown");
            };
            case "C" -> switch(this.move) {
                case "X" -> 6;
                case "Y" -> 0;
                case "Z" -> 3;
                default -> throw new RuntimeException("Unknown");
            };
            default -> throw new RuntimeException("Unknown");
        };
    }

}
