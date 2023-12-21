package edu.hw08.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

class FixedThreadPoolTest {
    private static final int THREADS_NUMBER = 4;
    private static final long[] FIBONACCI_NUMBERS = {
        0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L, 1597L, 2584L, 4181L, 6765L, 10946L, 17711L, 28657L, 46368L, 75025L, 121393L, 196418L, 317811L, 514229L, 832040L, 1346269L, 2178309L, 3524578L, 5702887L, 9227465L, 14930352L, 24157817L, 39088169L, 63245986L, 102334155L, 165580141L, 267914296L, 433494437L, 701408733L, 1134903170L, 1836311903L, 2971215073L, 4807526976L, 7778742049L, 12586269025L, 20365011074L, 32951280099L, 53316291173L, 86267571272L, 139583862445L, 225851433717L, 365435296162L, 591286729879L, 956722026041L, 1548008755920L, 2504730781961L, 4052739537881L, 6557470319842L, 10610209857723L, 17167680177565L, 27777890035288L, 44945570212853L, 72723460248141L, 117669030460994L, 190392490709135L, 308061521170129L, 498454011879264L, 806515533049393L, 1304969544928657L, 2111485077978050L, 3416454622906707L, 5527939700884757L, 8944394323791464L, 14472334024676221L, 23416728348467685L, 37889062373143906L, 61305790721611591L, 99194853094755497L, 160500643816367088L, 259695496911122585L, 420196140727489673L, 679891637638612258L, 1100087778366101931L, 1779979416004714189L, 2880067194370816120L, 4660046610375530309L, 7540113804746346429L
    };

    private FixedThreadPool fixedThreadPool;
    private volatile long[] results;

    @BeforeEach
    void setUp() {
        fixedThreadPool = FixedThreadPool.create(THREADS_NUMBER);
        fixedThreadPool.start();
    }

    @AfterEach
    void close() {
        try {
            sleep(1000);
            fixedThreadPool.close();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void fibonacciParallel() {
        // Arrange
        int n = 92;
        results = new long[n + 1];
        var latch = new CountDownLatch(n + 1);

        // Act
        for (int i = 0; i <= n; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                results[index] = fibonacci(index);
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException ignored) {
        }

        // Assert
        for (int i = 0; i <= n; ++i) {
            assertThat(results[i]).isEqualTo(FIBONACCI_NUMBERS[i]);
        }
    }

    private long fibonacci(int n) {
        if (n < 0 || n > 92) {
            throw new IllegalArgumentException("Valid range for n is [0, 92]");
        }

        if (n <= 1) {
            return n;
        }

        if (results[n] == 0) {
            results[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }

        return results[n];
    }
}
