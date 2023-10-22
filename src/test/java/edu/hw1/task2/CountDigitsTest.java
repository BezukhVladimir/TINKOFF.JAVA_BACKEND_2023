package edu.hw1.task2;

import edu.hw1.task2.NumberUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountDigitsTest {
    @Test
    @DisplayName("Counting digits in a single-digit number")
    void testCountOneDigit() {
        // Arrange
        int minPositiveNumber = 0;
        int maxPositiveNumber = 9;
        int minNegativeNumber = 0;
        int maxNegativeNumber = -9;

        // Act
        int result1 = NumberUtils.countDigits(minPositiveNumber);
        int result2 = NumberUtils.countDigits(maxPositiveNumber);
        int result3 = NumberUtils.countDigits(minNegativeNumber);
        int result4 = NumberUtils.countDigits(maxNegativeNumber);

        // Assert
        assertEquals(1, result1);
        assertEquals(1, result2);
        assertEquals(1, result3);
        assertEquals(1, result4);
    }

    @Test
    @DisplayName("Counting digits in a two-digit number")
    void testCountTwoDigits() {
        // Arrange
        int minPositiveNumber = 10;
        int maxPositiveNumber = 99;
        int minNegativeNumber = -10;
        int maxNegativeNumber = -99;

        // Act
        int result1 = NumberUtils.countDigits(minPositiveNumber);
        int result2 = NumberUtils.countDigits(maxPositiveNumber);
        int result3 = NumberUtils.countDigits(minNegativeNumber);
        int result4 = NumberUtils.countDigits(maxNegativeNumber);

        // Assert
        assertEquals(2, result1);
        assertEquals(2, result2);
        assertEquals(2, result3);
        assertEquals(2, result4);
    }

    @Test
    @DisplayName("Counting digits in a three-digit number")
    void testCountThreeDigits() {
        // Arrange
        int minPositiveNumber = 100;
        int maxPositiveNumber = 999;
        int minNegativeNumber = -100;
        int maxNegativeNumber = -999;

        // Act
        int result1 = NumberUtils.countDigits(minPositiveNumber);
        int result2 = NumberUtils.countDigits(maxPositiveNumber);
        int result3 = NumberUtils.countDigits(minNegativeNumber);
        int result4 = NumberUtils.countDigits(maxNegativeNumber);

        // Assert
        assertEquals(3, result1);
        assertEquals(3, result2);
        assertEquals(3, result3);
        assertEquals(3, result4);
    }

    @Test
    @DisplayName("Counting digits in a four-digit number")
    void testCountFourDigits() {
        // Arrange
        int minPositiveNumber = 1_000;
        int maxPositiveNumber = 9_999;
        int minNegativeNumber = -1_000;
        int maxNegativeNumber = -9_999;

        // Act
        int result1 = NumberUtils.countDigits(minPositiveNumber);
        int result2 = NumberUtils.countDigits(maxPositiveNumber);
        int result3 = NumberUtils.countDigits(minNegativeNumber);
        int result4 = NumberUtils.countDigits(maxNegativeNumber);

        // Assert
        assertEquals(4, result1);
        assertEquals(4, result2);
        assertEquals(4, result3);
        assertEquals(4, result4);
    }

    @Test
    @DisplayName("Counting digits in a five-digit number")
    void testCountFiveDigits() {
        // Arrange
        int minPositiveNumber = 10_000;
        int maxPositiveNumber = 99_999;
        int minNegativeNumber = -10_000;
        int maxNegativeNumber = -99_999;

        // Act
        int result1 = NumberUtils.countDigits(minPositiveNumber);
        int result2 = NumberUtils.countDigits(maxPositiveNumber);
        int result3 = NumberUtils.countDigits(minNegativeNumber);
        int result4 = NumberUtils.countDigits(maxNegativeNumber);

        // Assert
        assertEquals(5, result1);
        assertEquals(5, result2);
        assertEquals(5, result3);
        assertEquals(5, result4);
    }

    @Test
    @DisplayName("Counting digits in a six-digit number")
    void testCountSixDigits() {
        // Arrange
        int minPositiveNumber = 100_000;
        int maxPositiveNumber = 999_999;
        int minNegativeNumber = -100_000;
        int maxNegativeNumber = -999_999;

        // Act
        int result1 = NumberUtils.countDigits(minPositiveNumber);
        int result2 = NumberUtils.countDigits(maxPositiveNumber);
        int result3 = NumberUtils.countDigits(minNegativeNumber);
        int result4 = NumberUtils.countDigits(maxNegativeNumber);

        // Assert
        assertEquals(6, result1);
        assertEquals(6, result2);
        assertEquals(6, result3);
        assertEquals(6, result4);
    }

    @Test
    @DisplayName("Counting digits in a seven-digit number")
    void testCountSevenDigits() {
        // Arrange
        int minPositiveNumber = 1_000_000;
        int maxPositiveNumber = 9_999_999;
        int minNegativeNumber = -1_000_000;
        int maxNegativeNumber = -9_999_999;

        // Act
        int result1 = NumberUtils.countDigits(minPositiveNumber);
        int result2 = NumberUtils.countDigits(maxPositiveNumber);
        int result3 = NumberUtils.countDigits(minNegativeNumber);
        int result4 = NumberUtils.countDigits(maxNegativeNumber);

        // Assert
        assertEquals(7, result1);
        assertEquals(7, result2);
        assertEquals(7, result3);
        assertEquals(7, result4);
    }

    @Test
    @DisplayName("Counting digits in an eight-digit number")
    void testCountEightDigits() {
        // Arrange
        int minPositiveNumber = 10_000_000;
        int maxPositiveNumber = 99_999_999;
        int minNegativeNumber = -10_000_000;
        int maxNegativeNumber = -99_999_999;

        // Act
        int result1 = NumberUtils.countDigits(minPositiveNumber);
        int result2 = NumberUtils.countDigits(maxPositiveNumber);
        int result3 = NumberUtils.countDigits(minNegativeNumber);
        int result4 = NumberUtils.countDigits(maxNegativeNumber);

        // Assert
        assertEquals(8, result1);
        assertEquals(8, result2);
        assertEquals(8, result3);
        assertEquals(8, result4);
    }

    @Test
    @DisplayName("Counting digits in a nine-digit number")
    void testCountNineDigits() {
        // Arrange
        int minPositiveNumber = 100_000_000;
        int maxPositiveNumber = 999_999_999;
        int minNegativeNumber = -100_000_000;
        int maxNegativeNumber = -999_999_999;

        // Act
        int result1 = NumberUtils.countDigits(minPositiveNumber);
        int result2 = NumberUtils.countDigits(maxPositiveNumber);
        int result3 = NumberUtils.countDigits(minNegativeNumber);
        int result4 = NumberUtils.countDigits(maxNegativeNumber);

        // Assert
        assertEquals(9, result1);
        assertEquals(9, result2);
        assertEquals(9, result3);
        assertEquals(9, result4);
    }

    @Test
    @DisplayName("Counting digits in a ten-digit number")
    void testCountTenDigits() {
        // Arrange
        int minPositiveNumber = 1_000_000_000;
        int maxPositiveNumber = Integer.MAX_VALUE;
        int minNegativeNumber = -1_000_000_000;
        int maxNegativeNumber = Integer.MIN_VALUE;

        // Act
        int result1 = NumberUtils.countDigits(minPositiveNumber);
        int result2 = NumberUtils.countDigits(maxPositiveNumber);
        int result3 = NumberUtils.countDigits(minNegativeNumber);
        int result4 = NumberUtils.countDigits(maxNegativeNumber);

        // Assert
        assertEquals(10, result1);
        assertEquals(10, result2);
        assertEquals(10, result3);
        assertEquals(10, result4);
    }
}
