package com.example.bowling;

public class Game {
    private static int score;

    public void roll(int pins) {
        score += pins;
    }

    public int score() {
        return score;
    }
}
