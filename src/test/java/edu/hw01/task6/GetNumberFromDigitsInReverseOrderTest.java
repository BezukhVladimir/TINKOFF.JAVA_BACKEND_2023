package edu.hw01.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GetNumberFromDigitsInReverseOrderTest {
    @Test
    @DisplayName("Form a number from a range of digits")
    void testGetNumberFromDigits() {
        // Arrange
        int[] digits = {1, 2, 3, 4, 5};
        int startIndex = 1;
        int endIndex = 3;
        int expectedNumber = 432;

        // Act
        int resultNumber = NumberUtils.getNumberFromDigitsInReverseOrder(digits, startIndex, endIndex);

        // Assert
        assertThat(resultNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("Form a number from a single digit")
    void testGetNumberFromSingleDigit() {
        // Arrange
        int[] digits = {9};
        int startIndex = 0;
        int endIndex = 0;
        int expectedNumber = 9;

        // Act
        int resultNumber = NumberUtils.getNumberFromDigitsInReverseOrder(digits, startIndex, endIndex);

        // Assert
        assertThat(resultNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("Form a number from an empty range")
    void testGetNumberFromEmptyRange() {
        // Arrange
        int[] digits = {1, 2, 3};
        int startIndex = 2;
        int endIndex = 1;
        int expectedNumber = 0;

        // Act
        int resultNumber = NumberUtils.getNumberFromDigitsInReverseOrder(digits, startIndex, endIndex);

        // Assert
        assertThat(resultNumber).isEqualTo(expectedNumber);
    }
}
