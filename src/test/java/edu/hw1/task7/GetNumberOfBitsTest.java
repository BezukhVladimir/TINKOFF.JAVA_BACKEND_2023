package edu.hw1.task7;

import edu.hw1.task7.BitUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetNumberOfBitsTest {

    @Test
    @DisplayName("Test for powers of two")
    void testPowersOfTwo() {
        for (int i = 0; i < Integer.SIZE - 1; ++i) {
            // Arrange
            int number = (int) Math.pow(2, i);

            // Act
            int result = BitUtils.getNumberOfBits(number);

            // Assert
            assertEquals(i + 1, result);
        }
    }

    @Test
    @DisplayName("Test for negative numbers")
    void testNegativeNumbers() {
        for (int i = Integer.MIN_VALUE; i < 0; ++i) {
            // Act
            int result = BitUtils.getNumberOfBits(i);

            // Assert
            assertEquals(32, result);
        }
    }
}
