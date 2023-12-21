package edu.hw07.task2;

import edu.hw07.task2.multi.FactorialUtils;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MultiThreadedVersionTest {
    @Test
    public void factorial() {
        // Arrange
        int n = 20;
        long expected = 2432902008176640000L;

        // Act
        long result = FactorialUtils.factorial(n);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
