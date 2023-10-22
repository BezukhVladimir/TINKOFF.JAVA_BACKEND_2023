package edu.hw1.task5;

import edu.hw1.task5.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IsStringPalindromeTest {

    @Test
    @DisplayName("Palindrome string with even length")
    void testEvenLengthPalindrome() {
        // Arrange
        String palindrome = "abccba";

        // Act
        boolean result = StringUtils.isPalindrome(palindrome);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Palindrome string with odd length")
    void testOddLengthPalindrome() {
        // Arrange
        String palindrome = "abxba";

        // Act
        boolean result = StringUtils.isPalindrome(palindrome);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Non-palindrome string")
    void testNonPalindrome() {
        // Arrange
        String nonPalindrome = "palindrome";

        // Act
        boolean result = StringUtils.isPalindrome(nonPalindrome);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Empty string is a palindrome")
    void testEmptyStringIsPalindrome() {
        // Arrange
        String emptyString = "";

        // Act
        boolean result = StringUtils.isPalindrome(emptyString);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Single-character string is a palindrome")
    void testSingleCharacterIsPalindrome() {
        // Arrange
        String singleChar = "x";

        // Act
        boolean result = StringUtils.isPalindrome(singleChar);

        // Assert
        assertTrue(result);
    }
}
