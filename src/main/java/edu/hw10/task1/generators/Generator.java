package edu.hw10.task1.generators;

import java.lang.reflect.Parameter;

public abstract class Generator {
    private Generator next;

    public static Generator link(Generator first, Generator... chain) {
        Generator head = first;

        for (Generator nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }

        return first;
    }

    public abstract Object generate(Parameter parameter);

    protected Object generateNext(Parameter parameter) {
        if (next == null) {
            return null;
        }

        return next.generate(parameter);
    }
}
