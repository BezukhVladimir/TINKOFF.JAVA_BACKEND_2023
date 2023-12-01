package edu.hw7.task2;

import edu.hw7.task2.multi.FactorialUtils;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadedVersionTest {
    @Test
    public void testFactorial() {
        // Arrange
        int n = 20;
        long expected = 2432902008176640000L;

        // Act
        long result = FactorialUtils.factorial(n);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
