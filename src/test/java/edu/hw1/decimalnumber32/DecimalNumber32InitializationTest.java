package edu.hw1.decimalnumber32;

import edu.hw1.DecimalNumber32;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecimalNumber32InitializationTest {
    @Test
    @DisplayName("Create DecimalNumber32 instance with default value")
    void testCreateDefaultInstance() {
        // Act
        DecimalNumber32 number = new DecimalNumber32();

        // Assert
        assertTrue(number.getSign());
        assertEquals(0, number.getValue());
        assertEquals(0, number.getAbsoluteValue());
        assertEquals(1, number.getNumberOfDigits());
        assertArrayEquals(new int[]{0}, number.getDigits());
    }

    @Test
    @DisplayName("Create DecimalNumber32 instance with positive value")
    void testCreatePositiveInstance() {
        // Arrange
        int value = 42;

        // Act
        DecimalNumber32 number = new DecimalNumber32(value);

        // Assert
        assertTrue(number.getSign());
        assertEquals(value, number.getValue());
        assertEquals(value, number.getAbsoluteValue());
        assertEquals(2, number.getNumberOfDigits());
        assertArrayEquals(new int[]{4, 2}, number.getDigits());
    }

    @Test
    @DisplayName("Create DecimalNumber32 instance with negative value")
    void testCreateNegativeInstance() {
        // Arrange
        int value = -12345;

        // Act
        DecimalNumber32 number = new DecimalNumber32(value);

        // Assert
        assertFalse(number.getSign());
        assertEquals(value, number.getValue());
        assertEquals(-value, number.getAbsoluteValue());
        assertEquals(5, number.getNumberOfDigits());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, number.getDigits());
    }
}
