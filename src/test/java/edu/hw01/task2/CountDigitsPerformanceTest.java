package edu.hw01.task2;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The countDigits() method is about 10 times more performant than the classicCountDigits() method.
 * We should use countDigits() method. More precise performance testing is not required.
 */
@Disabled
class CountDigitsPerformanceTest {
    @Test
    @DisplayName("Executing countDigits() method for all 32-bit decimal integer numbers")
    void countDigits() {
        // Arrange
        int startNumber = Integer.MIN_VALUE;
        int endNumber = Integer.MAX_VALUE;

        // Act
        for (int i = startNumber; ; ++i) {
            int result = NumberUtils.countDigits(i);

            if (i == endNumber) {
                break;
            }
        }
    }

    @Test
    @DisplayName("Executing classicCountDigits() method for all 32-bit decimal integer numbers")
    void classicCountDigits() {
        // Arrange
        int startNumber = Integer.MIN_VALUE;
        int endNumber = Integer.MAX_VALUE;

        // Act
        for (int i = startNumber; ; ++i) {
            int result = NumberUtils.classicCountDigits(i);

            if (i == endNumber) {
                break;
            }
        }
    }
}
