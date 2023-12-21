package edu.hw01.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CountKTest {
    @Test
    @DisplayName("Tests for numbers in range 1000 to 9999")
    void inRange1000To9999() {
        // Arrange
        int startNumber = 1000;
        int endNumber = 9999;

        // Act
        for (int i = startNumber; i <= endNumber; ++i) {
            int result = KaprekarsRoutine.countK(i);

            if (NumberUtils.areAllDigitsSame(i)) {
                assertThat(result).isEqualTo(-1);
            } else {
                assertThat(result).isLessThanOrEqualTo(7);
                assertThat(result).isGreaterThanOrEqualTo(0);
            }
        }
    }
}
