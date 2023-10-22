package edu.hw3.task4;

import org.junit.jupiter.api.Test;
import static edu.hw3.task4.RomanNumeralsUtils.ROMAN_LARGE_NUMBERS;
import static edu.hw3.task4.RomanNumeralsUtils.ROMAN_MAX_VALUE;
import static edu.hw3.task4.RomanNumeralsUtils.ROMAN_NEGATIVE_NUMBERS;
import static edu.hw3.task4.RomanNumeralsUtils.ROMAN_ZERO;
import static edu.hw3.task4.RomanNumeralsUtils.convertToRoman;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralsUtilsTest {
    @Test
    void testNegativeNumber() {
        // Arrange
        int number = -1;

        // Act
        String romanNumber = convertToRoman(number);

        // Assert
        assertEquals(ROMAN_NEGATIVE_NUMBERS, romanNumber);
    }

    @Test
    void testZero() {
        // Arrange
        int number = 0;

        // Act
        String romanNumber = convertToRoman(number);

        // Assert
        assertEquals(ROMAN_ZERO, romanNumber);
    }

    @Test
    void testLargeNumber() {
        // Arrange
        int number = 4000;

        // Act
        String romanNumber = convertToRoman(number);

        // Assert
        assertEquals(ROMAN_LARGE_NUMBERS, romanNumber);
    }

    @Test
    void testMaxValue() {
        // Act
        String romanNumber = convertToRoman(ROMAN_MAX_VALUE);

        // Assert
        assertEquals("MMMCMXCIX", romanNumber);
    }
}
