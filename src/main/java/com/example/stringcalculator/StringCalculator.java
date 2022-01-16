package com.example.stringcalculator;

import java.util.Scanner;

public class StringCalculator {

    public int add(String numbers) {

        if (numbers.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(numbers);
        }


    }
}
