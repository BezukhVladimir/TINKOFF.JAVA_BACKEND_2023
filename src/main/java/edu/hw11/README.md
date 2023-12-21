# Домашнее задание №11
В этом задании мы будем работать с генерацией и редактированием байткода JVM.

Понятно, что делать это мы будем не на уровне битов и байтов, а использовать готовые методы и классы.

Для задания нам потребуется библиотека ByteByddy, которую нужно подключить как зависимость в pom.xml:

    <properties>
        <bytebuddy.version>1.14.10</bytebuddy.version>
        <asm.version>9.6</asm.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.ow2.asm</groupId>
            <artifactId>asm</artifactId>
            <version>${asm.version}</version>
        </dependency>
    
        <dependency>
            <groupId>net.bytebuddy</groupId>
            <artifactId>byte-buddy-agent</artifactId>
            <version>${bytebuddy.version}</version>
        </dependency>
        <dependency>
            <groupId>net.bytebuddy</groupId>
            <artifactId>byte-buddy</artifactId>
            <version>${bytebuddy.version}</version>
        </dependency>
    </dependencies>

Большинство заданий можно реализовывать сразу в виде тестов.
* примеры использования: https://github.com/raphw/byte-buddy/blob/master/byte-buddy-dep/src/test/java/net/bytebuddy/ByteBuddyTutorialExamplesTest.java
* официальные уроки: http://bytebuddy.net/#/tutorial

## Задание 1
При помощи библиотеки ByteBuddy создайте новый класс, метод toString которого выводит "Hello, ByteBuddy!".

## Задание 2
При помощи библиотеки ByteBuddy напишите функцию, которая изменяет поведение существующего класса на лету. Например, в классе:

    class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }

вместо + будет производиться операция *.

## Задание 3
При помощи библиотеки ByteBuddy cоздайте новый класс и метод с сигнатурой long fib(int n), для генерации кода требуется использовать класс ByteCodeAppender.

В задании может пригодиться оф. документация: https://asm.ow2.io/developer-guide.html

Вы не должны использовать делегаты к методам других классов. Функцию нужно написать при помощи классов библиотеки ASM:

    MethodVisitor mv = new MethodVisitor(...);
    mv.visitVarInsn(Opcodes.ILOAD,1);
    mv.visitJumpInsn(Opcodes.IFEQ,...);
    // ...
    mv.visitInsn(Opcodes.LRETURN);
