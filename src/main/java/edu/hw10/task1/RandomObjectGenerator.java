package edu.hw10.task1;

import edu.hw10.task1.generators.Generator;
import edu.hw10.task1.generators.IntegerGenerator;
import edu.hw10.task1.generators.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;

public class RandomObjectGenerator {
    private final Generator generator;

    public RandomObjectGenerator() {
        this.generator = Generator.link(
            new IntegerGenerator(),
            new StringGenerator()
        );
    }

    public RandomObjectGenerator(Generator generator) {
        this.generator = generator;
    }

    public <T> T nextObject(Class<T> target)
        throws InvocationTargetException,
               InstantiationException,
               IllegalAccessException {
        var constructor = Arrays.stream(target.getDeclaredConstructors())
            .max(Comparator.comparingInt(Constructor::getParameterCount))
            .orElseThrow();

        constructor.setAccessible(true);
        return (T) constructor.newInstance(getArgs(constructor));
    }

    public <T> T nextObject(Class<T> target, String creationMethodName)
        throws InvocationTargetException,
               InstantiationException,
               IllegalAccessException {
        if (creationMethodName == null) {
            return nextObject(target);
        }

        Method createMethod = Arrays.stream(target.getDeclaredMethods())
            .filter(method -> method.getName().equals(creationMethodName))
            .findFirst()
            .orElseThrow();

        createMethod.setAccessible(true);
        return (T) createMethod.invoke(null, getArgs(createMethod));
    }

    private Object[] getArgs(Executable method) {
        Parameter[] parameters = method.getParameters();

        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; ++i) {
            args[i] = generator.generate(parameters[i]);
        }

        return args;
    }
}
