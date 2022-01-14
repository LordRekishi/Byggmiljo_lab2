package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int score;
    private final List<Integer> rolls = new ArrayList<>();

    public void roll(int pins) {
        spare(pins);
        rolls.add(pins);
    }

    private void spare(int pins) {
        if (rolls.size() > 1 && rolls.size() % 2 == 0) {
            if (rolls.get(rolls.size() - 2) + rolls.get(rolls.size() - 1) == 10) {
                score += pins;
            }
        }
    }

    public int score() {
        for (Integer roll : rolls) {
            score += roll;
        }
        return score;
    }
}
