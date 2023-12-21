package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    void helloByteBuddy() throws Exception {
        String string = "Hello, ByteBuddy!";

        DynamicType.Unloaded<Object> unloadedType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.isToString())
            .intercept(FixedValue.value(string))
            .make();

        Class<?> dynamicType = unloadedType
            .load(getClass().getClassLoader())
            .getLoaded();

        assertThat(dynamicType.getDeclaredConstructor().newInstance().toString()).isEqualTo(string);
    }
}
