package com.example.stringcalculator;

import java.util.stream.Stream;

public class StringCalculator {

    public int add(String numbers) {
        String delimiter = ",|\n";

        if (numbers.equals("")) {
            return 0;
        }

        if (numbers.startsWith("//")) {
            delimiter = numbers.charAt(2) + "|\n";
        }

        if (numbers.contains("-")) {
            StringBuilder negatives = getStringOfNegatives(numbers, delimiter);
            throw new RuntimeException("Negatives " + negatives + " not allowed");
        }

        return getSum(numbers, delimiter);
    }

    private int getSum(String numbers, String delimiter) {
        return Stream.of(numbers.split(delimiter))
                .filter(this::isNumeric)
                .mapToInt(Integer::parseInt)
                .filter(value -> value <= 1000)
                .sum();
    }

    private StringBuilder getStringOfNegatives(String numbers, String delimiter) {
        StringBuilder negatives = new StringBuilder();

        negatives.append("( ");
        Stream.of(numbers.split(delimiter))
                .filter(s -> s.contains("-"))
                .forEach(s -> negatives.append(s).append(" "));
        negatives.append(")");

        return negatives;
    }

    private boolean isNumeric(String input) {
        if (input == null)
            return false;

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
