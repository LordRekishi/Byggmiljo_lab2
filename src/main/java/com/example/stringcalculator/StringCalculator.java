package com.example.stringcalculator;

public class StringCalculator {

    public int add(String numbers) {

        if (numbers.equals("")) {
            return 0;
        } else {
            int sum = 0;
            for (char c : numbers.toCharArray()) {
                if (isNumeric(c))
                    sum += Integer.parseInt(String.valueOf(c));
            }
            return sum;
        }

    }

    private boolean isNumeric(char input) {

        try {
            Integer.parseInt(String.valueOf(input));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
