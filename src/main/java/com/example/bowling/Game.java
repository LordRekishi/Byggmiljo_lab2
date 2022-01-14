package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Integer> rolls = new ArrayList<>();
    private int MAX_ROLLS = 20;
    private int score;
    private int rollCounter;

    public void roll(int pins) {
        if (rollCounter < MAX_ROLLS) {
            if (rollCounter == 18 && pins == 10) {
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
        if (rolls.get(rolls.size() - 2) == 10 && rollCounter == 20) {
          score -= (rolls.get(rolls.size() - 1) + pins);
        }
        if (rolls.get(rolls.size() - 2) == 10) {
            score += (rolls.get(rolls.size() - 1) + pins);
            rollCounter++;
        }
    }

    private void spare(int pins) {
        if (rolls.size() % 2 == 0) {
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
