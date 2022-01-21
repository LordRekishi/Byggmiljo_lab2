package com.example.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void changeDelimiterShouldReturnThirteen() {
        int result = stringCalculator.add("//;\n11;2");

        assertThat(result).isEqualTo(13);
    }

    @Test
    void addOneNegativeNumberShouldThrowException() {
        assertThatThrownBy(() -> stringCalculator.add("-1"))
                .hasMessageContaining("Negatives").hasMessageContaining("not").hasMessageContaining("allowed");
    }

    @Test
    void addMultipleNegativesShouldThrowExceptionAndListAllNegatives() {
        assertThatThrownBy(() -> stringCalculator.add("-1,-1,2,3,-2")).hasMessageContaining("Negatives ( -1 -1 -2 ) not allowed");
    }

    @Test
    void addNumberLargerThan1000ShouldNotCount() {
        int result = stringCalculator.add("//;2;3;1000;1001");

        assertThat(result).isEqualTo(1005);
    }

    @Test
    void longDelimiterShouldReturnSix() {
        int result = stringCalculator.add("//[***]\n1***2***3");

        assertThat(result).isEqualTo(6);
    }

    @Test
    void multipleDelimitersShouldReturnSix() {
        int result = stringCalculator.add("//[*][%]\n1*2%3");

        assertThat(result).isEqualTo(6);
    }

    @Test
    void multipleDelimitersWithLengthLongerThanOneCharacterShouldReturnSix() {
        int result = stringCalculator.add("//[*****][%%%%%%%%]\n1*****2%%%%%%%%3");

        assertThat(result).isEqualTo(6);
    }
}
