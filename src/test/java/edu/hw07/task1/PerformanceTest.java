package edu.hw07.task1;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class PerformanceTest {
    private static final int EXPECTED = 400_000_000;

    @Test
    public void singleThreadedIncrement() {
        // Arrange
        var count = new edu.hw07.task1.single.Counter(0);

        // Act
        for (int j = 0; j < EXPECTED; ++j) {
            count.increment();
        }

        // Assert
        assertThat(count.getValue()).isEqualTo(EXPECTED);
    }

    @Test
    public void multiThreadedIncrement() {
        // Arrange
        var count = new edu.hw07.task1.multi.Counter(0);
        int numberOfThreads = 4;
        int iterationsPerThread = EXPECTED / numberOfThreads;

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
        assertThat(count.getValue()).isEqualTo(EXPECTED);
    }
}
