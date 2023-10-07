package edu.hw1.bitutils;

import edu.hw1.BitUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotateLeftTest {

    @Test
    @DisplayName("Test rotating left by 1 bit")
    void testByOneBit() {
        // Arrange
        int input1 = 0;
        int input2 = Integer.MAX_VALUE;
        int input3 = 16;
        int shift1 = 1;
        int shift2 = -1;

        // Act
        int result11 = BitUtils.rotateLeft(input1, shift1);
        int result12 = BitUtils.rotateLeft(input2, shift1);
        int result13 = BitUtils.rotateLeft(input3, shift1);
        int result21 = BitUtils.rotateLeft(input1, shift2);
        int result22 = BitUtils.rotateLeft(input2, shift2);
        int result23 = BitUtils.rotateLeft(input3, shift2);

        // Assert
        assertEquals(0, result11);
        assertEquals(Integer.MAX_VALUE, result12);
        assertEquals(1, result13);
        assertEquals(0, result21);
        assertEquals(Integer.MAX_VALUE, result22);
        assertEquals(8, result23);
    }

    @Test
    @DisplayName("Input number is negative")
    void testNegativeNumber() {
        // Arrange
        int input = -1;
        int shift = 2;

        // Act
        int result = BitUtils.rotateLeft(input, shift);

        // Assert
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Shift equal to number of bits")
    void testWithShiftEqualToNumberOfBits() {
        // Arrange
        int input = 16;
        int shift = BitUtils.getNumberOfBits(input);

        // Act
        int result = BitUtils.rotateLeft(input, shift);

        // Assert
        assertEquals(input, result);
    }
}
