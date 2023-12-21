package edu.hw03.task1;

public final class AtbashCipherUtils {
    private AtbashCipherUtils() {
    }

    public static String atbash(String input) {
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(getMirroredChar(c));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    private static char getMirroredChar(char c) {
        if (Character.isUpperCase(c)) {
            return (char) ('Z' - (c - 'A'));
        } else {
            return (char) ('z' - (c - 'a'));
        }
    }
}
