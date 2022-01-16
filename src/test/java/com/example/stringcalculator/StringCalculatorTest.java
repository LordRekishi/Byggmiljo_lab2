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
}
