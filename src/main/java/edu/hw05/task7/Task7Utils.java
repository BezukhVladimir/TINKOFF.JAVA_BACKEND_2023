package edu.hw05.task7;

/**
 * Напишите регулярные выражения для строк из алфавита {0, 1}.
 *
 * Subtask 1: содержит не менее 3 символов, причем третий символ равен 0.
 * Subtask 2: начинается и заканчивается одним и тем же символом.
 * Subtask 3: длина не менее 1 и не более 3.
 */
public final class Task7Utils {
    private Task7Utils() {
    }

    private static final String SUBTASK_1_REGEX_PATTERN = "[01]{2}0[01]*";
    private static final String SUBTASK_2_REGEX_PATTERN = "(0|1).*\\1";
    private static final String SUBTASK_3_REGEX_PATTERN = "[01]{1,3}";

    public static boolean isStringForSubtask1(String string) {
        return string.matches(SUBTASK_1_REGEX_PATTERN);
    }

    public static boolean isStringForSubtask2(String string) {
        return string.matches(SUBTASK_2_REGEX_PATTERN);
    }

    public static boolean isStringForSubtask3(String string) {
        return string.matches(SUBTASK_3_REGEX_PATTERN);
    }
}
