package com.example.stringcalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {

    public int add(String numbers) {
        String delimiter = ",|\n";

        if (numbers.equals("")) {
            return 0;
        }

        if (numbers.startsWith("//[")){
            Pattern pattern = Pattern.compile("\\[.*?]");
            Matcher matcher = pattern.matcher(numbers);

            StringBuilder stringBuilder = new StringBuilder();

            while (matcher.find()) {
                stringBuilder.append(matcher.group(), 1, matcher.group().length() - 1).append("|");
            }
            stringBuilder.append("\n");

            delimiter = stringBuilder.toString();
        } else if (numbers.startsWith("//") && !numbers.startsWith("//[")) {
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
