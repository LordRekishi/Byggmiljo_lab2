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

        delimiter = setDelimiterIfNotDefault(numbers, delimiter);
        checkForNegatives(numbers, delimiter);

        return getSum(numbers, delimiter);
    }

    private String setDelimiterIfNotDefault(String numbers, String delimiter) {
        if (numbers.startsWith("//[")) {
            Pattern pattern = Pattern.compile("\\[.*?]");
            Matcher matcher = pattern.matcher(numbers);
            delimiter = buildDelimiterString(matcher);
        } else if (numbers.startsWith("//")) {
            delimiter = numbers.charAt(2) + "|\n";
        }
        return delimiter;
    }

    private String buildDelimiterString(Matcher matcher) {
        String delimiter;
        StringBuilder stringBuilder = new StringBuilder();

        while (matcher.find()) {
            stringBuilder.append("\\Q")
                    .append(matcher.group(), 1, matcher.group().length() - 1)
                    .append("\\E")
                    .append("|");
        }
        stringBuilder.append("\n");

        delimiter = stringBuilder.toString();
        return delimiter;
    }

    private void checkForNegatives(String numbers, String delimiter) {
        if (numbers.contains("-")) {
            StringBuilder negatives = getStringOfNegatives(numbers, delimiter);
            throw new RuntimeException("Negatives " + negatives + " not allowed");
        }
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

    private int getSum(String numbers, String delimiter) {
        return Stream.of(numbers.split(delimiter))
                .filter(this::isNumeric)
                .mapToInt(Integer::parseInt)
                .filter(value -> value <= 1000)
                .sum();
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
