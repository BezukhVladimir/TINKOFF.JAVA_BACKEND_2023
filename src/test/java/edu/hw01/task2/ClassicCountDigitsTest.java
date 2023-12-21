package edu.hw01.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ClassicCountDigitsTest {
    @Test
    @DisplayName("Count digits in a positive number")
    void countPositiveNumber() {
        // Arrange
        int number = 12345;

        // Act
        int result = NumberUtils.classicCountDigits(number);

        // Assert
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Count digits in a negative number")
    void countNegativeNumber() {
        // Arrange
        int number = -9876;

        // Act
        int result = NumberUtils.classicCountDigits(number);

        // Assert
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Count digits in zero")
    void countZero() {
        // Arrange
        int number = 0;

        // Act
        int result = NumberUtils.classicCountDigits(number);

        // Assert
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Count digits in a large positive number")
    void countLargePositiveNumber() {
        // Arrange
        int number = 1234567890;

        // Act
        int result = NumberUtils.classicCountDigits(number);

        // Assert
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("Count digits in a large negative number")
    void countLargeNegativeNumber() {
        // Arrange
        int number = -987654321;

        // Act
        int result = NumberUtils.classicCountDigits(number);

        // Assert
        assertThat(result).isEqualTo(9);
    }
}
