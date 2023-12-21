package edu.hw07.task1.single;

public final class Counter {
    private int value;

    public Counter(int initialValue) {
        this.value = initialValue;
    }

    public int getValue() {
        return value;
    }

    public void increment() {
        value++;
    }
}
