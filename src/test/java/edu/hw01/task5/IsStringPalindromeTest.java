package edu.hw01.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class IsStringPalindromeTest {
    @Test
    @DisplayName("Palindrome string with even length")
    void testEvenLengthPalindrome() {
        // Arrange
        String palindrome = "abccba";

        // Act
        boolean result = StringUtils.isPalindrome(palindrome);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Palindrome string with odd length")
    void testOddLengthPalindrome() {
        // Arrange
        String palindrome = "abxba";

        // Act
        boolean result = StringUtils.isPalindrome(palindrome);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Non-palindrome string")
    void testNonPalindrome() {
        // Arrange
        String nonPalindrome = "palindrome";

        // Act
        boolean result = StringUtils.isPalindrome(nonPalindrome);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Empty string is a palindrome")
    void testEmptyStringIsPalindrome() {
        // Arrange
        String emptyString = "";

        // Act
        boolean result = StringUtils.isPalindrome(emptyString);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Single-character string is a palindrome")
    void testSingleCharacterIsPalindrome() {
        // Arrange
        String singleChar = "x";

        // Act
        boolean result = StringUtils.isPalindrome(singleChar);

        // Assert
        assertThat(result).isTrue();
    }
}
