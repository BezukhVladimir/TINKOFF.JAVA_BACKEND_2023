package edu.hw1.task6;

import edu.hw1.task6.ArrayUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReverseTest {
    @Test
    @DisplayName("Reversing an empty array")
    void testReverseEmptyArray() {
        // Arrange
        int[] array = {};

        // Act
        ArrayUtils.reverse(array);

        // Assert
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    @DisplayName("Reversing an array with one element")
    void testReverseArrayWithOneElement() {
        // Arrange
        int[] array = {42};

        // Act
        ArrayUtils.reverse(array);

        // Assert
        assertArrayEquals(new int[]{42}, array);
    }

    @Test
    @DisplayName("Reversing an array with even number of elements")
    void testReverseArrayWithEvenNumberOfElements() {
        // Arrange
        int[] array = {1, 2, 3, 4, 5, 6};

        // Act
        ArrayUtils.reverse(array);

        // Assert
        assertArrayEquals(new int[]{6, 5, 4, 3, 2, 1}, array);
    }

    @Test
    @DisplayName("Reversing an array with odd number of elements")
    void testReverseArrayWithOddNumberOfElements() {
        // Arrange
        int[] array = {1, 2, 3, 4, 5};

        // Act
        ArrayUtils.reverse(array);

        // Assert
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, array);
    }

    @Test
    @DisplayName("Reversing an array with repeated elements")
    void testReverseArrayWithRepeatedElements() {
        // Arrange
        int[] array = {5, 5, 5, 5, 5, 5};

        // Act
        ArrayUtils.reverse(array);

        // Assert
        assertArrayEquals(new int[]{5, 5, 5, 5, 5, 5}, array);
    }
}
