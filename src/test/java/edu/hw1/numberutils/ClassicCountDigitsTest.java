package edu.hw1.numberutils;

import edu.hw1.NumberUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ClassicCountDigitsTest {
    @Test
    @DisplayName("Count digits in a positive number")
    void testCountPositiveNumber() {
        // Arrange
        int number = 12345;

        // Act
        int result = NumberUtils.classicCountDigits(number);

        // Assert
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Count digits in a negative number")
    void testCountNegativeNumber() {
        // Arrange
        int number = -9876;

        // Act
        int result = NumberUtils.classicCountDigits(number);

        // Assert
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Count digits in zero")
    void testCountZero() {
        // Arrange
        int number = 0;

        // Act
        int result = NumberUtils.classicCountDigits(number);

        // Assert
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Count digits in a large positive number")
    void testCountLargePositiveNumber() {
        // Arrange
        int number = 1234567890;

        // Act
        int result = NumberUtils.classicCountDigits(number);

        // Assert
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("Count digits in a large negative number")
    void testCountLargeNegativeNumber() {
        // Arrange
        int number = -987654321;

        // Act
        int result = NumberUtils.classicCountDigits(number);

        // Assert
        assertThat(result).isEqualTo(9);
    }
}
