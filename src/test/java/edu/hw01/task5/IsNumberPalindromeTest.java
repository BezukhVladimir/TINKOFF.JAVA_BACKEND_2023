package edu.hw01.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IsNumberPalindromeTest {
    @Test
    @DisplayName("Palindrome number")
    void testPositivePalindrome() {
        // Arrange
        int number = 12321;

        // Act
        boolean result = NumberUtils.isPalindrome(number);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Negative number is a non-palindrome")
    void testNegativeNonPalindrome() {
        // Arrange
        int number = -1221;

        // Act
        boolean result = NumberUtils.isPalindrome(number);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Asymmetric number is a non-palindrome")
    void testAsymmetricNonPalindrome() {
        // Arrange
        int number = 12345;

        // Act
        boolean result = NumberUtils.isPalindrome(number);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Single-digit is a palindrome")
    void testSingleDigitIsPalindrome() {
        // Arrange
        int number = 7;

        // Act
        boolean result = NumberUtils.isPalindrome(number);

        // Assert
        assertThat(result).isTrue();
    }
}
