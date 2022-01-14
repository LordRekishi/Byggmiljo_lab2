package com.example;

import com.example.bowling.Game;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
    void fullGameWithOneSpareAtStartShouldReturnTwenty() {
        game.roll(5);
        game.roll(5);
        game.roll(5);
        fullGame(17, 0);

        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    void fullGameWithTwoSparesShouldReturnThirtyFive() {
        game.roll(5);
        game.roll(5);
        game.roll(5);
        game.roll(5);
        game.roll(5);
        fullGame(15, 0);

        assertThat(game.score()).isEqualTo(35);
    }

    @Test
    void fullGameWithTwoSparesSeperatedShouldReturnForty() {
        game.roll(5);
        game.roll(5);
        game.roll(5);
        fullGame(13, 0);
        game.roll(5);
        game.roll(5);
        game.roll(5);
        game.roll(0);

        assertThat(game.score()).isEqualTo(40);
    }

    @Test
    void fullGameWithOneStrikeAtStartShouldReturnEighteen() {
        game.roll(10);
        game.roll(2);
        game.roll(2);
        fullGame(16, 0);

        assertThat(game.score()).isEqualTo(18);
    }

    @Test
    void fullGameWithStrikeInFrameTenShouldReturn14() {
        fullGame(18, 0);
        game.roll(10);
        game.roll(2);
        game.roll(2);

        assertThat(game.score()).isEqualTo(14);
    }

    @Test
    void rollTooManyTimesShouldReturnTwenty() {
        fullGame(30, 1);

        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    void rollWithTooManyPinsShouldReturnZero() {
        fullGame(20, 20);

        assertThat(game.score()).isEqualTo(0);
    }
}
