package edu.hw1.numberutils;

import edu.hw1.NumberUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IsPalindromeTest {
    @Test
    @DisplayName("Palindrome number")
    void testPositivePalindrome() {
        // Arrange
        int number = 12321;

        // Act
        boolean result = NumberUtils.isPalindrome(number);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Negative number is a non-palindrome")
    void testNegativeNonPalindrome() {
        // Arrange
        int number = -1221;

        // Act
        boolean result = NumberUtils.isPalindrome(number);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Asymmetric number is a non-palindrome")
    void testAsymmetricNonPalindrome() {
        // Arrange
        int number = 12345;

        // Act
        boolean result = NumberUtils.isPalindrome(number);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Single-digit is a palindrome")
    void testSingleDigitIsPalindrome() {
        // Arrange
        int number = 7;

        // Act
        boolean result = NumberUtils.isPalindrome(number);

        // Assert
        assertTrue(result);
    }
}
