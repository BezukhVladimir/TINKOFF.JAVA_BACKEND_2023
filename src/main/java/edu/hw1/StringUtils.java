package edu.hw1;

public final class StringUtils {

    private StringUtils() {
    }

    /**
     * Checks if the given string is a palindrome.
     *
     * @param string The string to check for palindrome.
     * @return true if the string is a palindrome, otherwise false.
     */
    public static boolean isPalindrome(String string) {
        int halfLength = string.length() / 2;

        for (int i = 0; i < halfLength; ++i) {
            if (string.charAt(i) != string.charAt(string.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }
}
