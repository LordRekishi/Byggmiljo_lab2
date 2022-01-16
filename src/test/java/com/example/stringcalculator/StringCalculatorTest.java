package com.example.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();

    @Test
    void addEmptyStingShouldReturnZero() {
        int result = stringCalculator.add("");

        assertThat(result).isEqualTo(0);
    }

    @Test
    void addOneNumberShouldReturnOne() {
        int result = stringCalculator.add("1");

        assertThat(result).isEqualTo(1);
    }

    @Test
    void addTwoNumbersShouldReturnThree() {
        int result = stringCalculator.add("1,2");

        assertThat(result).isEqualTo(3);
    }

    @Test
    void addFiveNumbersShouldReturnFive() {
        int result = stringCalculator.add("1,1,1,1,1");

        assertThat(result).isEqualTo(5);
    }

    @Test
    void addNumbersWithEscapeCharactersShouldReturnSix() {
        int result = stringCalculator.add("1\n2,3");

        assertThat(result).isEqualTo(6);
    }
}
