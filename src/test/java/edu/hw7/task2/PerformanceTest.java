package edu.hw7.task2;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class PerformanceTest {
    @Test
    public void testSingleThreadedFactorial() {
        // Arrange
        int n = 20;
        long expected = 2432902008176640000L;

        // Act
        long result = edu.hw7.task2.single.FactorialUtils.factorial(n);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testMultiThreadedIncrement() {
        // Arrange
        int n = 20;
        long expected = 2432902008176640000L;

        // Act
        long result = edu.hw7.task2.multi.FactorialUtils.factorial(n);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
