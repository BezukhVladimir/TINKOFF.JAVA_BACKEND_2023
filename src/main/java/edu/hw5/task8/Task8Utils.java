package edu.hw5.task8;

/**
 * Напишите регулярные выражения для строк из алфавита {0, 1}.
 *
 * Subtask 1: нечетной длины.
 * Subtask 2: начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину.
 * Subtask 3: количество 0 кратно 3 (если 0 ноль, то true).
 * Subtask 4: любая строка, кроме 11 или 111.
 * Subtask 5: каждый нечетный символ равен 1.
 * Subtask 6: содержит не менее двух 0 и не более одной 1.
 * Subtask 7: нет последовательных 1.
 */
public final class Task8Utils {
    private Task8Utils() {
    }

    private final static String SUBTASK_1_REGEX_PATTERN = "(?:[01]{2})*[01]";
    private final static String SUBTASK_2_REGEX_PATTERN = "0(?:[01]{2})*|1[01](?:[01]{2})*";
    private final static String SUBTASK_3_REGEX_PATTERN = "(?:(?:1*0){3})*1*";
    private final static String SUBTASK_4_REGEX_PATTERN = "(?!11$|111$)[01]*";
    private final static String SUBTASK_5_REGEX_PATTERN = "(?:1[01])*1?";
    private final static String SUBTASK_6_REGEX_PATTERN = "(?=(?:1*01*){2,})(?=(?:0*10*)?$)[01]*";
    private final static String SUBTASK_7_REGEX_PATTERN = "(?!0*11)[01]*";

    public static boolean isStringForSubtask1(String string) {
        return string.matches(SUBTASK_1_REGEX_PATTERN);
    }

    public static boolean isStringForSubtask2(String string) {
        return string.matches(SUBTASK_2_REGEX_PATTERN);
    }

    public static boolean isStringForSubtask3(String string) {
        return string.matches(SUBTASK_3_REGEX_PATTERN);
    }

    public static boolean isStringForSubtask4(String string) {
        return string.matches(SUBTASK_4_REGEX_PATTERN);
    }

    public static boolean isStringForSubtask5(String string) {
        return string.matches(SUBTASK_5_REGEX_PATTERN);
    }

    public static boolean isStringForSubtask6(String string) {
        return string.matches(SUBTASK_6_REGEX_PATTERN);
    }

    public static boolean isStringForSubtask7(String string) {
        return string.matches(SUBTASK_7_REGEX_PATTERN);
    }
}
