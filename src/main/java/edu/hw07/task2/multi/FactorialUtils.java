package edu.hw07.task2.multi;

import java.util.stream.LongStream;

public final class FactorialUtils {
    private FactorialUtils() {
    }

    private static final int MAX_N = 20;

    public static long factorial(int n) {
        if (n < 0 || n > MAX_N) {
            throw new IllegalArgumentException();
        }

        if (n == 0) {
            return 1L;
        }

        return LongStream
            .rangeClosed(1, n)
            .parallel()
            .reduce(1L, (long a, long b) -> a * b);
    }
}
