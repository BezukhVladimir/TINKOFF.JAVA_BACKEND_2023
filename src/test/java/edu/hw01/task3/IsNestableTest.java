package edu.hw01.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IsNestableTest {
    @Test
    @DisplayName("First array can be nested inside the second array")
    void testNestableArrays() {
        // Arrange
        int[] arr1 = {-5, 0, 3, 7};
        int[] arr2 = {-10, 10};

        // Act
        boolean result = ArrayUtils.isNestable(arr1, arr2);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Arrays cannot be nested (min1 == min2)")
    void testNonNestableMin1() {
        // Arrange
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 10};

        // Act
        boolean result = ArrayUtils.isNestable(arr1, arr2);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Arrays cannot be nested (min1 < min2)")
    void testNonNestableMin2() {
        // Arrange
        int[] arr1 = {0, 2, 3};
        int[] arr2 = {1, 10};

        // Act
        boolean result = ArrayUtils.isNestable(arr1, arr2);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Arrays cannot be nested (max1 == max2)")
    void testNonNestableMax1() {
        // Arrange
        int[] arr1 = {1, 2, 3, 10};
        int[] arr2 = {0, 10};

        // Act
        boolean result = ArrayUtils.isNestable(arr1, arr2);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Arrays cannot be nested (max1 > max2)")
    void testNonNestableMax2() {
        // Arrange
        int[] arr1 = {1, 2, 3, 11};
        int[] arr2 = {0, 10};

        // Act
        boolean result = ArrayUtils.isNestable(arr1, arr2);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Empty array cannot be nested")
    void testNonNestableWithEmptyFirstArray() {
        // Arrange
        int[] arr1 = {};
        int[] arr2 = {1, 2, 3};

        // Act
        boolean result = ArrayUtils.isNestable(arr1, arr2);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("First array cannot be nested inside an empty second array")
    void testNonNestableWithEmptySecondArray() {
        // Arrange
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {};

        // Act
        boolean result = ArrayUtils.isNestable(arr1, arr2);

        // Assert
        assertThat(result).isFalse();
    }
}
