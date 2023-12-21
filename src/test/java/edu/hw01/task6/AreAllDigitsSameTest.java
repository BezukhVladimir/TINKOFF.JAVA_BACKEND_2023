package edu.hw01.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AreAllDigitsSameTest {
    @Test
    @DisplayName("Check if all digits are the same (positive number)")
    void testPositive() {
        // Arrange
        int number = 11111;

        // Act
        boolean result = NumberUtils.areAllDigitsSame(number);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Check if all digits are the same (negative number)")
    void testNegative() {
        // Arrange
        int number = -777;

        // Act
        boolean result = NumberUtils.areAllDigitsSame(number);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Check if all digits are not the same")
    void testAreAllDigitsNotSame() {
        // Arrange
        int number = 12345;

        // Act
        boolean result = NumberUtils.areAllDigitsSame(number);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Check if all digits are the same (single-digit number)")
    void testSingleDigit() {
        // Arrange
        int number = 7;

        // Act
        boolean result = NumberUtils.areAllDigitsSame(number);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Check if all digits are the same (zero)")
    void testZero() {
        // Arrange
        int number = 0;

        // Act
        boolean result = NumberUtils.areAllDigitsSame(number);

        // Assert
        assertThat(result).isTrue();
    }
}
