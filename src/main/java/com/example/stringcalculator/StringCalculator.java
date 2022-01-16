package com.example.stringcalculator;

public class StringCalculator {

    public int add(String numbers) {

        if (numbers.equals("")) {
            return 0;
        } else if (numbers.contains(",")) {
            return Integer.parseInt(numbers.substring(0, 1)) + Integer.parseInt(numbers.substring(2, 3));
        } else {
            return Integer.parseInt(numbers);
        }

    }
}
