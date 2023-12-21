package edu.hw03.task4;

import org.junit.jupiter.api.Test;
import static edu.hw03.task4.RomanNumeralsUtils.ROMAN_LARGE_NUMBERS;
import static edu.hw03.task4.RomanNumeralsUtils.ROMAN_MAX_VALUE;
import static edu.hw03.task4.RomanNumeralsUtils.ROMAN_NEGATIVE_NUMBERS;
import static edu.hw03.task4.RomanNumeralsUtils.ROMAN_ZERO;
import static edu.hw03.task4.RomanNumeralsUtils.convertToRoman;
import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralsUtilsTest {
    @Test
    void testNegativeNumber() {
        // Arrange
        int number = -1;

        // Act
        String romanNumber = convertToRoman(number);

        // Assert
        assertThat(romanNumber).isEqualTo(ROMAN_NEGATIVE_NUMBERS);
    }

    @Test
    void testZero() {
        // Arrange
        int number = 0;

        // Act
        String romanNumber = convertToRoman(number);

        // Assert
        assertThat(romanNumber).isEqualTo(ROMAN_ZERO);
    }

    @Test
    void testLargeNumber() {
        // Arrange
        int number = 4000;

        // Act
        String romanNumber = convertToRoman(number);

        // Assert
        assertThat(romanNumber).isEqualTo(ROMAN_LARGE_NUMBERS);
    }

    @Test
    void testMaxValue() {
        // Act
        String romanNumber = convertToRoman(ROMAN_MAX_VALUE);

        // Assert
        assertThat(romanNumber).isEqualTo("MMMCMXCIX");
    }
}
