package edu.hw05.task6;

import org.junit.jupiter.api.Test;
import static edu.hw05.task6.SubsequenceChecker.isSubsequence;
import static org.assertj.core.api.Assertions.assertThat;

public final class SubsequenceCheckerTest {
    @Test
    void testIsSubsequence() {
        // Arrange
        String subsequence = "abc";
        String sequence = "achfdbaabgabcaabg";

        // Act
        boolean result = isSubsequence(subsequence, sequence);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    void testIsNotSubsequence() {
        // Arrange
        String subsequence = "abc";
        String sequence = "achfdbaabgaxcaabg";

        // Act
        boolean result = isSubsequence(subsequence, sequence);

        // Assert
        assertThat(result).isFalse();
    }
}
