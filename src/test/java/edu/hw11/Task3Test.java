package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.lang.reflect.Modifier;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    private static final long[] FIBONACCI_NUMBERS = {
        0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L
    };
    private static final String CLASS_NAME = "FibonacciCalculator";
    private static final String METHOD_NAME = "fibonacci";
    private static final String METHOD_SIGNATURE = "(I)J"; // long ...(int n)

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 })
    void createClassWithASM(int n) throws Exception {
        var fibonacciCalculator = new ByteBuddy()
            .subclass(Object.class)
            .name(CLASS_NAME)
            .defineMethod(METHOD_NAME, long.class, Modifier.PUBLIC | Modifier.STATIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new FibonacciAppender()))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        long result = (long) fibonacciCalculator
            .getMethod(METHOD_NAME, int.class)
            .invoke(null, n);

        assertThat(result).isEqualTo(FIBONACCI_NUMBERS[n]);
    }

    private static class FibonacciAppender implements ByteCodeAppender {
        @Override
        public Size apply(
            MethodVisitor mv,
            Implementation.Context context,
            MethodDescription md
        ) {
            mv.visitCode();

            // if (n <= 1)
            Label l1 = new Label();
            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, l1);

            // return n
            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitInsn(Opcodes.I2L);
            mv.visitInsn(Opcodes.LRETURN);

            // if (n > 1)
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

            // fib(n - 1)
            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitInsn(Opcodes.ISUB);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, CLASS_NAME, METHOD_NAME, METHOD_SIGNATURE);

            // fib(n - 2)
            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitInsn(Opcodes.ICONST_2);
            mv.visitInsn(Opcodes.ISUB);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, CLASS_NAME, METHOD_NAME, METHOD_SIGNATURE);

            // fib(n - 1) + fib(n - 2);
            mv.visitInsn(Opcodes.LADD);
            mv.visitInsn(Opcodes.LRETURN);
            mv.visitEnd();

            return new ByteCodeAppender.Size(4, 1);
        }
    }
}
