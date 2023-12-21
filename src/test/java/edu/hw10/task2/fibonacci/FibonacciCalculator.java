package edu.hw10.task2.fibonacci;

import edu.hw10.task2.Cache;

public interface FibonacciCalculator {
    @Cache(persist = false)
    long fibonacci(int number);
}
