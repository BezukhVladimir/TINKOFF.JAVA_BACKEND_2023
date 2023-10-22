package edu.hw3.task1;

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
        char mirroredChar;

        if (Character.isUpperCase(c)) {
            mirroredChar = (char) ('Z' - (c - 'A'));
        } else {
            mirroredChar = (char) ('z' - (c - 'a'));
        }

        return mirroredChar;
    }
}
