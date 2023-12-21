package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class Task2Test {
    @Test
    public void redefineMethod() throws Exception {
        ByteBuddyAgent.install();
        var normalBehavior = new ArithmeticUtils();

        try {
            new ByteBuddy()
                .redefine(AmogusArithmeticUtils.class)
                .name(ArithmeticUtils.class.getName())
                .make()
                .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

            assertThat(normalBehavior.sum(2, 1)).isEqualTo(2);
        } finally {
            ClassReloadingStrategy.fromInstalledAgent().reset(ArithmeticUtils.class);
        }

        assertThat(normalBehavior.sum(2, 1)).isEqualTo(3);
    }

    public static class ArithmeticUtils {
        public static int sum(int a, int b) {
            return a + b;
        }
    }

    public static class AmogusArithmeticUtils {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}
