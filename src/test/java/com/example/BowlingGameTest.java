package com.example;

import com.example.bowling.Game;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {

    @Test
    void fullGameNoScoreShouldReturnZero() {
        Game game = new Game();

        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }

        assertThat(game.score()).isEqualTo(0);
    }
}
