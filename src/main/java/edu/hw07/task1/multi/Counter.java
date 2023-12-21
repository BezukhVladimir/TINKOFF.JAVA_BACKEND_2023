package edu.hw07.task1.multi;

import java.util.concurrent.atomic.AtomicInteger;

public final class Counter {
    private AtomicInteger value;

    public Counter(int initialValue) {
        value = new AtomicInteger(initialValue);
    }

    public int getValue() {
        return value.get();
    }

    public void increment() {
        value.incrementAndGet();
    }
}
