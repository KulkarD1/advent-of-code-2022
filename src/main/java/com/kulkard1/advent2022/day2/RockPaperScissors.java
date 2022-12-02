package com.kulkard1.advent2022.day2;

public class RockPaperScissors {

    String opponentMove;

    String move;

    public RockPaperScissors(String line) {
        String[] moves = line.split(" ");
        this.opponentMove = moves[0];
        this.move = getMoveFromResult(moves[1]);
    }

    private String getMoveFromResult(String result) {
        return switch (result) {
            case "X" -> switch(this.opponentMove) {
                case "A" -> "C";
                case "B" -> "A";
                case "C" -> "B";
                default -> throw new RuntimeException("Unknown");
            };
            case "Y" -> this.opponentMove;
            case "Z" -> switch(this.opponentMove) {
                case "A" -> "B";
                case "B" -> "C";
                case "C" -> "A";
                default -> throw new RuntimeException("Unknown");
            };
            default -> throw new RuntimeException("Unknown");
        };
    }

    public int getScore() {
        return getMovePoints() + getResultPoints();
    }

    private int getMovePoints() {
        return switch (this.move) {
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            default -> throw new RuntimeException("Unknown");
        };
    }

    private int getResultPoints() {
        return switch (this.opponentMove) {
            case "A" -> switch(this.move) {
                case "A" -> 3;
                case "B" -> 6;
                case "C" -> 0;
                default -> throw new RuntimeException("Unknown");
            };
            case "B" -> switch(this.move) {
                case "A" -> 0;
                case "B" -> 3;
                case "C" -> 6;
                default -> throw new RuntimeException("Unknown");
            };
            case "C" -> switch(this.move) {
                case "A" -> 6;
                case "B" -> 0;
                case "C" -> 3;
                default -> throw new RuntimeException("Unknown");
            };
            default -> throw new RuntimeException("Unknown");
        };
    }

}
