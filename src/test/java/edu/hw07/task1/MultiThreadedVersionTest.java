package edu.hw07.task1;

import edu.hw07.task1.multi.Counter;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

class MultiThreadedVersionTest {
    @Test
    public void increment() {
        // Arrange
        var count = new Counter(0);
        int expected = 1;

        // Act
        count.increment();

        // Assert
        assertThat(count.getValue()).isEqualTo(expected);
    }

    @Test
    public void multiThreadedIncrement() throws InterruptedException {
        // Arrange
        var count = new Counter(0);
        int numberOfThreads = Runtime.getRuntime().availableProcessors() - 1;
        var threads = new Thread[numberOfThreads];
        int iterationsPerThread = 1_000;
        int expected = numberOfThreads * iterationsPerThread;
        var latch = new CountDownLatch(numberOfThreads);

        // Act
        for (int i = 0; i < numberOfThreads; ++i) {
            threads[i] = new Thread(() -> {
                latch.countDown();

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                for (int j = 0; j < iterationsPerThread; ++j) {
                    count.increment();
                }
            });
            threads[i].start();
        }

        for (var thread : threads) {
            thread.join();
        }

        // Assert
        assertThat(count.getValue()).isEqualTo(expected);
    }
}
