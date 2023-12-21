package edu.hw10.task2;

import java.nio.file.Path;
import edu.hw10.task2.fibonacci.FibonacciCalculator;
import edu.hw10.task2.fibonacci.RecursiveFibonacciCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static edu.hw06.FilesUtils.deleteFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class CacheProxyTest {
    private Path cacheDirectory = Path.of("src/test/java/edu/hw10/task2/");

    private static final long[] FIBONACCI_NUMBERS = {
        0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L
    };

    @AfterEach
    void deleteCacheFile() {
        deleteFile(cacheDirectory.resolve("cache.txt"));
    }

    @Test
    void functionIsInvokedOnce() {
        // Arrange
        FibonacciCalculator mock = mock(RecursiveFibonacciCalculator.class);
        FibonacciCalculator proxy = CacheProxy.create(mock, mock.getClass(), cacheDirectory);

        for (int i = 0; i < FIBONACCI_NUMBERS.length; ++i) {
            // Arrange
            when(mock.fibonacci(i)).thenReturn(FIBONACCI_NUMBERS[i]);

            // Act && Assert
            assertThat(proxy.fibonacci(i)).isEqualTo(FIBONACCI_NUMBERS[i]);
            assertThat(proxy.fibonacci(i)).isEqualTo(FIBONACCI_NUMBERS[i]);
            assertThat(proxy.fibonacci(i)).isEqualTo(FIBONACCI_NUMBERS[i]);

            // Assert
            Mockito.verify(mock, Mockito.times(1)).fibonacci(i);
        }
    }

    @Test
    void getValuesFromCache() {
        // Arrange
        FibonacciCalculator mock = mock(RecursiveFibonacciCalculator.class);
        FibonacciCalculator proxy = CacheProxy.create(mock, mock.getClass(), cacheDirectory);

        for (int i = 0; i < FIBONACCI_NUMBERS.length; ++i) {
            // Arrange
            when(mock.fibonacci(i)).thenReturn(FIBONACCI_NUMBERS[i]);

            // Act && Assert
            assertThat(proxy.fibonacci(i)).isEqualTo(FIBONACCI_NUMBERS[i]);
            assertThat(proxy.fibonacci(i)).isEqualTo(FIBONACCI_NUMBERS[i]);
            assertThat(proxy.fibonacci(i)).isEqualTo(FIBONACCI_NUMBERS[i]);

            // Assert
            Mockito.verify(mock, Mockito.times(1)).fibonacci(i);
        }

        // Arrange
        reset(mock);
        FibonacciCalculator newProxy = CacheProxy.create(mock, mock.getClass(), cacheDirectory);

        for (int i = 0; i < FIBONACCI_NUMBERS.length; ++i) {
            // Act && Assert
            assertThat(newProxy.fibonacci(i)).isEqualTo(FIBONACCI_NUMBERS[i]);

            // Assert
            Mockito.verify(mock, Mockito.times(0)).fibonacci(i);
        }
    }
}
