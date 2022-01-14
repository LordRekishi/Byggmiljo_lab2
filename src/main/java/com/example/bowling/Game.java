package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Integer> rolls = new ArrayList<>();
    private final int MAX_PINS = 10;
    private int MAX_ROLLS = 20;
    private int score;
    private int rollCounter;

    public void roll(int pins) {
        if (rollCounter < MAX_ROLLS && pins <= MAX_PINS) {
            if (rollCounter == 18 && pins == MAX_PINS) {
                MAX_ROLLS++;
            }
            if (rolls.size() > 1) {
                strike(pins);
                spare(pins);
            }
            rolls.add(pins);
            rollCounter++;
        }
    }

    private void strike(int pins) {
        if (rolls.get(rolls.size() - 2) == MAX_PINS && rollCounter == 20) {
          score -= (rolls.get(rolls.size() - 1) + pins);
        }
        if (rolls.get(rolls.size() - 2) == MAX_PINS) {
            score += (rolls.get(rolls.size() - 1) + pins);
            rollCounter++;
        }
    }

    private void spare(int pins) {
        if (rolls.size() % 2 == 0) {
            if (rolls.get(rolls.size() - 2) + rolls.get(rolls.size() - 1) == MAX_PINS) {
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
