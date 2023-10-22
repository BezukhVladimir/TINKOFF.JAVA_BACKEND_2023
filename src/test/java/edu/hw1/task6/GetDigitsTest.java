package edu.hw1.task6;

import edu.hw1.task6.NumberUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GetDigitsTest {
    @Test
    @DisplayName("Get digits of a positive number")
    void testPositiveNumber() {
        // Arrange
        int number = 12345;
        int[] expectedDigits = {1, 2, 3, 4, 5};

        // Act
        int[] resultDigits = NumberUtils.getDigits(number);

        // Assert
        assertArrayEquals(expectedDigits, resultDigits);
    }

    @Test
    @DisplayName("Get digits of a negative number")
    void testNegativeNumber() {
        // Arrange
        int number = -54321;
        int[] expectedDigits = {5, 4, 3, 2, 1};

        // Act
        int[] resultDigits = NumberUtils.getDigits(number);

        // Assert
        assertArrayEquals(expectedDigits, resultDigits);
    }

    @Test
    @DisplayName("Get digits of a zero number")
    void testGetDigitsZeroNumber() {
        // Arrange
        int number = 0;
        int[] expectedDigits = {0};

        // Act
        int[] resultDigits = NumberUtils.getDigits(number);

        // Assert
        assertArrayEquals(expectedDigits, resultDigits);
    }
}
