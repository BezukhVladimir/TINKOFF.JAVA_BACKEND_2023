package edu.hw1.task6;

import edu.hw1.task6.NumberUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AreAllDigitsSameTest {
    @Test
    @DisplayName("Check if all digits are the same (positive number)")
    void testPositive() {
        // Arrange
        int number = 11111;

        // Act
        boolean result = NumberUtils.areAllDigitsSame(number);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if all digits are the same (negative number)")
    void testNegative() {
        // Arrange
        int number = -777;

        // Act
        boolean result = NumberUtils.areAllDigitsSame(number);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if all digits are not the same")
    void testAreAllDigitsNotSame() {
        // Arrange
        int number = 12345;

        // Act
        boolean result = NumberUtils.areAllDigitsSame(number);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Check if all digits are the same (single-digit number)")
    void testSingleDigit() {
        // Arrange
        int number = 7;

        // Act
        boolean result = NumberUtils.areAllDigitsSame(number);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if all digits are the same (zero)")
    void testZero() {
        // Arrange
        int number = 0;

        // Act
        boolean result = NumberUtils.areAllDigitsSame(number);

        // Assert
        assertTrue(result);
    }
}
