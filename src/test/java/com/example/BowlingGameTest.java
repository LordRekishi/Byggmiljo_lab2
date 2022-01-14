package com.example;

import com.example.bowling.Game;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    Game game = new Game();

    private void fullGame(int rolls, int pins) {
        for (int i = 0; i < rolls; i++) {
            game.roll(pins);
        }
    }

    @Test
    void fullGameNoScoreShouldReturnZero() {
        fullGame(20, 0);
        assertThat(game.score()).isEqualTo(0);
    }

    @Test
    void fullGameAllOnesShouldReturnTwenty() {
        fullGame(20, 1);
        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    void fullGameWithOneSpareShouldReturnTwenty() {
        game.roll(5);
        game.roll(5);
        game.roll(5);
        fullGame(17, 0);
        assertThat(game.score()).isEqualTo(20);
    }
}
