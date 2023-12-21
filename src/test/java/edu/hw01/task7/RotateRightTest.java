package edu.hw01.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class RotateRightTest {
    @ParameterizedTest
    @CsvSource({
        "0, 1, 0",
        "0, -1, 0",
        "16, 1, 8",
        "16, -1, 1",
        "2147483647, 1, 2147483647",
        "2147483647, -1, 2147483647"
    })
    @DisplayName("Test rotating right by 1 bit")
    void testRotateRightByOneBit(int input, int shift, int expected) {
        // Act
        int result = BitUtils.rotateRight(input, shift);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Input number is negative")
    void testNegativeNumber() {
        // Arrange
        int input = -1;
        int shift = 2;
        int expected = -1;

        // Act
        int result = BitUtils.rotateRight(input, shift);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Shift equal to number of bits")
    void testWithShiftEqualToNumberOfBits() {
        // Arrange
        int input = 16;
        int shift = BitUtils.getNumberOfBits(input);

        // Act
        int result = BitUtils.rotateRight(input, shift);

        // Assert
        assertThat(result).isEqualTo(input);
    }
}
