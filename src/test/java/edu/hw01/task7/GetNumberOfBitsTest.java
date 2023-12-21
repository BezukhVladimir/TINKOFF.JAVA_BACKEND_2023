package edu.hw01.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GetNumberOfBitsTest {
    @Test
    @DisplayName("Test for powers of two")
    void powersOfTwo() {
        for (int i = 0; i < Integer.SIZE - 1; ++i) {
            // Arrange
            int number = (int) Math.pow(2, i);

            // Act
            int result = BitUtils.getNumberOfBits(number);

            // Assert
            assertThat(result).isEqualTo(i + 1);
        }
    }

    @Test
    @DisplayName("Test for negative numbers")
    void negativeNumbers() {
        for (int i = Integer.MIN_VALUE; i < 0; ++i) {
            // Act
            int result = BitUtils.getNumberOfBits(i);

            // Assert
            assertEquals(result, 32);
        }
    }
}
