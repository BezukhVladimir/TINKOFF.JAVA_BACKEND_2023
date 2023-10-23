package edu.hw1.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CountDigitsTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 9, 0, -9})
    @DisplayName("Counting digits in a single-digit number")
    void testCountOneDigit(int input) {
        // Act
        int result = NumberUtils.countDigits(input);

        // Assert
        assertThat(result).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 99, -10, -99})
    @DisplayName("Counting digits in a two-digit number")
    void testCountTwoDigits(int input) {
        // Act
        int result = NumberUtils.countDigits(input);

        // Assert
        assertThat(result).isEqualTo(2);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 999, -100, -999})
    @DisplayName("Counting digits in a three-digit number")
    void testCountThreeDigits(int input) {
        // Act
        int result = NumberUtils.countDigits(input);

        // Assert
        assertThat(result).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1_000, 9_999, -1_000, -9_999})
    @DisplayName("Counting digits in a four-digit number")
    void testCountFourDigits(int input) {
        // Act
        int result = NumberUtils.countDigits(input);

        // Assert
        assertThat(result).isEqualTo(4);
    }

    @ParameterizedTest
    @ValueSource(ints = {10_000, 99_999, -10_000, -99_999})
    @DisplayName("Counting digits in a five-digit number")
    void testCountFiveDigits(int input) {
        // Act
        int result = NumberUtils.countDigits(input);

        // Assert
        assertThat(result).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {100_000, 999_999, -100_000, -999_999})
    @DisplayName("Counting digits in a six-digit number")
    void testCountSixDigits(int input) {
        // Act
        int result = NumberUtils.countDigits(input);

        // Assert
        assertThat(result).isEqualTo(6);
    }

    @ParameterizedTest
    @ValueSource(ints = {1_000_000, 9_999_999, -1_000_000, -9_999_999})
    @DisplayName("Counting digits in a seven-digit number")
    void testCountSevenDigits(int input) {
        // Act
        int result = NumberUtils.countDigits(input);

        // Assert
        assertThat(result).isEqualTo(7);
    }

    @ParameterizedTest
    @ValueSource(ints = {10_000_000, 99_999_999, -10_000_000, -99_999_999})
    @DisplayName("Counting digits in an eight-digit number")
    void testCountEightDigits(int input) {
        // Act
        int result = NumberUtils.countDigits(input);

        // Assert
        assertThat(result).isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(ints = {100_000_000, 999_999_999, -100_000_000, -999_999_999})
    @DisplayName("Counting digits in a nine-digit number")
    void testCountNineDigits(int input) {
        // Act
        int result = NumberUtils.countDigits(input);

        // Assert
        assertThat(result).isEqualTo(9);
    }

    @ParameterizedTest
    @ValueSource(ints = {1_000_000_000, Integer.MAX_VALUE, -1_000_000_000, Integer.MIN_VALUE})
    @DisplayName("Counting digits in a ten-digit number")
    void testCountTenDigits(int input) {
        // Act
        int result = NumberUtils.countDigits(input);

        // Assert
        assertThat(result).isEqualTo(10);
    }
}
