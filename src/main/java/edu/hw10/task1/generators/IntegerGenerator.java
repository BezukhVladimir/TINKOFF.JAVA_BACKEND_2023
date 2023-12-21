package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"InnerAssignment"})
public class IntegerGenerator extends Generator {
    @Override
    public Object generate(Parameter parameter) {
        if (!isInteger(parameter)) {
            return generateNext(parameter);
        }

        var minValue = Integer.MIN_VALUE;
        var maxValue = Integer.MAX_VALUE;
        for (var annotation : parameter.getAnnotations()) {
            switch (annotation) {
                case Min minAnnotation -> minValue = minAnnotation.value();
                case Max maxAnnotation -> maxValue = maxAnnotation.value();
                default -> { }
            }
        }

        return ThreadLocalRandom.current().nextInt(minValue, maxValue);
    }

    private static boolean isInteger(Parameter parameter) {
        return parameter.getType().equals(int.class) || parameter.getType().equals(Integer.class);
    }
}
