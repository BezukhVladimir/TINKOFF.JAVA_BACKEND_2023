package edu.hw3.task4;

import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("MagicNumber")
public final class RomanNumeralsUtils {
    public static final String ROMAN_NEGATIVE_NUMBERS = "The Romans didn't invent a symbol for negative numbers.";
    public static final String ROMAN_ZERO = "Nulla";
    public static final int ROMAN_MAX_VALUE = 3999;
    public static final String ROMAN_LARGE_NUMBERS =
        "The Romans developed two main ways of writing large numbers, "
            + "the apostrophus and the vinculum, further extended in various ways in later times.";
    public static final Map<String, Integer> ROMAN_NUMERALS = new LinkedHashMap<>() {{
        put("M", 1000);
        put("CM", 900);
        put("D", 500);
        put("CD", 400);
        put("C", 100);
        put("XC", 90);
        put("L", 50);
        put("XL", 40);
        put("X", 10);
        put("IX", 9);
        put("V", 5);
        put("IV", 4);
        put("I", 1);
    }};

    private RomanNumeralsUtils() {
    }

    public static String convertToRoman(int number) {
        if (number < 0) {
            return ROMAN_NEGATIVE_NUMBERS;
        }

        if (number == 0) {
            return ROMAN_ZERO;
        }

        if (number > ROMAN_MAX_VALUE) {
            return ROMAN_LARGE_NUMBERS;
        }

        return getRomanNumber(number);
    }

    private static @NotNull String getRomanNumber(int number) {
        int value = number;
        StringBuilder romanNumber = new StringBuilder();

        for (var entry : ROMAN_NUMERALS.entrySet()) {
            while (value >= entry.getValue()) {
                romanNumber.append(entry.getKey());
                value -= entry.getValue();
            }
        }

        return romanNumber.toString();
    }
}
