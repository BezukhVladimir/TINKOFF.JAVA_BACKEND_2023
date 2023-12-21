package edu.hw10.task2.fibonacci;

import edu.hw10.task2.Cache;

public class RecursiveFibonacciCalculator implements FibonacciCalculator {
    @Override
    @Cache(persist = true)
    public long fibonacci(int n) {
        if (n < 0 || n > 92) {
            throw new IllegalArgumentException("Valid range for n is [0, 92]");
        }

        if (n <= 1) {
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
