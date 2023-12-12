package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.NotNull;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator extends Generator {
    private static final List<String> WORDS = List.of(
        "",
        "Apple",
        "Orange",
        "Banana"
    );

    @Override
    public Object generate(Parameter parameter) {
        if (!isString(parameter)) {
            return generateNext(parameter);
        }

        for (var annotation : parameter.getAnnotations()) {
            if (annotation instanceof NotNull) {
                return "NotNull" + randomWord();
            }
        }

        return ThreadLocalRandom.current().nextBoolean()
            ? randomWord()
            : null;
    }

    private static boolean isString(Parameter parameter) {
        return parameter.getType().equals(String.class);
    }

    private static String randomWord() {
        return WORDS.get(ThreadLocalRandom.current().nextInt(WORDS.size()));
    }
}
