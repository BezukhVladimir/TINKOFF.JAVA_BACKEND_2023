package edu.hw1.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReverseTest {
    @Test
    @DisplayName("Reversing an empty array")
    void testReverseEmptyArray() {
        // Arrange
        int[] array = {};
        int[] expectedArray = new int[] {};

        // Act
        ArrayUtils.reverse(array);

        // Assert
        assertThat(array).isEqualTo(expectedArray);
    }

    @Test
    @DisplayName("Reversing an array with one element")
    void testReverseArrayWithOneElement() {
        // Arrange
        int[] array = {42};
        int[] expectedArray = new int[] {42};

        // Act
        ArrayUtils.reverse(array);

        // Assert
        assertThat(array).isEqualTo(expectedArray);
    }

    @Test
    @DisplayName("Reversing an array with even number of elements")
    void testReverseArrayWithEvenNumberOfElements() {
        // Arrange
        int[] array = {1, 2, 3, 4, 5, 6};
        int[] expectedArray = new int[] {6, 5, 4, 3, 2, 1};

        // Act
        ArrayUtils.reverse(array);

        // Assert
        assertThat(array).isEqualTo(expectedArray);
    }

    @Test
    @DisplayName("Reversing an array with odd number of elements")
    void testReverseArrayWithOddNumberOfElements() {
        // Arrange
        int[] array = {1, 2, 3, 4, 5};
        int[] expectedArray = new int[] {5, 4, 3, 2, 1};

        // Act
        ArrayUtils.reverse(array);

        // Assert
        assertThat(array).isEqualTo(expectedArray);
    }

    @Test
    @DisplayName("Reversing an array with repeated elements")
    void testReverseArrayWithRepeatedElements() {
        // Arrange
        int[] array = {5, 5, 5, 5, 5, 5};
        int[] expectedArray = new int[] {5, 5, 5, 5, 5, 5};

        // Act
        ArrayUtils.reverse(array);

        // Assert
        assertThat(array).isEqualTo(expectedArray);
    }
}
