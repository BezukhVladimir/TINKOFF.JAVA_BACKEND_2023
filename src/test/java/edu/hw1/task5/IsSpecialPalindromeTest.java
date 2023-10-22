package edu.hw1.task5;

import static org.junit.jupiter.api.Assertions.*;
import edu.hw1.task5.SpecialPalindromeValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IsSpecialPalindromeTest {

    @Test
    @DisplayName("Valid special palindrome")
    void testValidSpecialPalindrome() {
        // Arrange
        int validSpecialPalindrome = 11211230;

        // Act
        boolean result = SpecialPalindromeValidator.isSpecialPalindrome(validSpecialPalindrome);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Valid palindrome")
    void testValidPalindrome() {
        // Arrange
        int validPalindrome = 121;

        // Act
        boolean result = SpecialPalindromeValidator.isSpecialPalindrome(validPalindrome);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Non-special palindrome")
    void testNonSpecialPalindrome() {
        // Arrange
        int nonSpecialPalindrome = 45;

        // Act
        boolean result = SpecialPalindromeValidator.isSpecialPalindrome(nonSpecialPalindrome);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Number with odd digits")
    void testNumberWithOddDigits() {
        // Arrange
        int numberWithOddDigits = 14725;

        // Act
        boolean result = SpecialPalindromeValidator.isSpecialPalindrome(numberWithOddDigits);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Number with only one digit")
    void testNumberWithOneDigit() {
        // Arrange
        int numberWithOneDigit = 5;

        // Act
        boolean result = SpecialPalindromeValidator.isSpecialPalindrome(numberWithOneDigit);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Negative number is a non-palindrome")
    void testNegativeNumber() {
        // Arrange
        int numberWithOneDigit = -1;

        // Act
        boolean result = SpecialPalindromeValidator.isSpecialPalindrome(numberWithOneDigit);

        // Assert
        assertFalse(result);
    }
}
