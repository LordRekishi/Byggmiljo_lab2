package com.example.bowling;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
                MAX_ROLLS += 2;
            }
            if (rollCounter == 19 && rolls.get(rolls.size() - 1) + pins == 10) {
                MAX_ROLLS++;
            }
            strike(pins);

            rolls.add(pins);
            rollCounter++;
        }
    }

    private void strike(int pins) {
        if (pins == MAX_PINS) {
            rollCounter++;
        }
    }

    public int score() {

        int count = 0;
        for (int i = 0; i < rollCounter; i++) {
            if (count < 20) {
                score += rolls.get(i);
            }

            if (count % 2 == 0) {
                if (rolls.get(i) == 10) {
                    score += rolls.get(i + 1) + rolls.get(i + 2);
                    count++;
                }

                if (rolls.get(i) + rolls.get(i + 1) == 10) {
                    score += rolls.get(i + 2);
                }
            }
            count++;
        }
        return score;
    }
}
