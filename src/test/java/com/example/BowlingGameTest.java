package com.example;

import com.example.bowling.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void rollSpareThenStrikeShouldReturnTwentyfive() {
        game.roll(5);
        game.roll(5);
        game.roll(10);
        fullGame(16, 1);

        assertThat(game.score()).isEqualTo(48);
    }

    @ParameterizedTest
    @MethodSource("framesProvider")
    void validNumberOfThrowsShouldGiveCorrectScore(List<Integer> pins, int result) {
        Game game = new Game();
        for (Integer pin : pins) {
            game.roll(pin);
        }

        assertEquals(result, game.score());
    }

    static Stream<Arguments> framesProvider() {
        return Stream.of(
                Arguments.of(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0),
                Arguments.of(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 5, 5), 20),
                Arguments.of(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5), 15),
                Arguments.of(List.of(6, 1, 4, 6, 4, 6, 7, 3, 7, 3, 10, 10, 5, 4, 10, 1, 2), 144),
                Arguments.of(List.of(6, 1, 4, 6, 4, 6, 7, 3, 7, 0, 10, 10, 5, 4, 10, 10, 7, 3), 162),
                Arguments.of(List.of(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10), 300)
        );
    }
}
