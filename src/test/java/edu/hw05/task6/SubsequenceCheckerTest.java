package edu.hw05.task6;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class SubsequenceCheckerTest {
    @Test
    void isSubsequence() {
        // Arrange
        String subsequence = "abc";
        String sequence = "achfdbaabgabcaabg";

        // Act
        boolean result = SubsequenceChecker.isSubsequence(subsequence, sequence);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    void isNotSubsequence() {
        // Arrange
        String subsequence = "abc";
        String sequence = "achfdbaabgaxcaabg";

        // Act
        boolean result = SubsequenceChecker.isSubsequence(subsequence, sequence);

        // Assert
        assertThat(result).isFalse();
    }
}
