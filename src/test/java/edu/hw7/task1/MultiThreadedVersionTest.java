package edu.hw7.task1;

import edu.hw7.task1.multi.Counter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadedVersionTest {
    @Test
    public void testIncrement() {
        // Arrange
        var count = new Counter(0);
        int expected = 1;

        // Act
        count.increment();

        // Assert
        assertThat(count.getValue()).isEqualTo(expected);
    }

    @Test
    public void testMultiThreadedIncrement() {
        // Arrange
        var count = new Counter(0);
        int numberOfThreads = Runtime.getRuntime().availableProcessors() - 1;
        int iterationsPerThread = 1_000;
        int expected = numberOfThreads * iterationsPerThread;

        Thread[] incrementors = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; ++i) {
            incrementors[i] = new Thread(() -> {
                for (int j = 0; j < iterationsPerThread; ++j) {
                    count.increment();
                }
            });
        }

        // Act
        for (Thread incrementor : incrementors) {
            incrementor.start();
        }

        try {
            for (Thread incrementor : incrementors) {
                incrementor.join();
            }
        } catch (InterruptedException ignored) {
        }

        // Assert
        assertThat(count.getValue()).isEqualTo(expected);
    }
}
