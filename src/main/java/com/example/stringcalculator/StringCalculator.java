package com.example.stringcalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {
    private String delimiter = ",|\n";

    public int add(String numbers) {

        if (numbers.equals("")) {
            return 0;
        }

        setDelimiterIfNotDefault(numbers);
        checkForNegatives(numbers);

        return getSum(numbers);
    }

    private void setDelimiterIfNotDefault(String numbers) {
        if (numbers.startsWith("//[")) {
            Pattern pattern = Pattern.compile("\\[.*?]");
            Matcher matcher = pattern.matcher(numbers);
            buildDelimiterString(matcher);
        } else if (numbers.startsWith("//")) {
            delimiter = numbers.charAt(2) + "|\n";
        }
    }

    private void buildDelimiterString(Matcher matcher) {
        StringBuilder stringBuilder = new StringBuilder();

        while (matcher.find()) {
            stringBuilder.append("\\Q")
                    .append(matcher.group(), 1, matcher.group().length() - 1)
                    .append("\\E")
                    .append("|");
        }
        stringBuilder.append("\n");

        delimiter = stringBuilder.toString();
    }

    private void checkForNegatives(String numbers) {
        if (numbers.contains("-")) {
            StringBuilder negatives = getStringOfNegatives(numbers);
            throw new RuntimeException("Negatives " + negatives + " not allowed");
        }
    }

    private StringBuilder getStringOfNegatives(String numbers) {
        StringBuilder negatives = new StringBuilder();

        negatives.append("( ");
        Stream.of(numbers.split(delimiter))
                .filter(s -> s.contains("-"))
                .forEach(s -> negatives.append(s).append(" "));
        negatives.append(")");

        return negatives;
    }

    private int getSum(String numbers) {
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
