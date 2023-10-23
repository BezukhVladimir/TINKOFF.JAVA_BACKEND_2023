package edu.hw1.task5;

public final class NumberUtils {

    private NumberUtils() {
    }

    public static final int BASE_10 = 10;

    /**
     * Checks if the given number is a palindrome.
     *
     * A palindrome is a positive number that remains the same even after reversing.
     *
     * @param number The number to check for palindrome.
     * @return true if the number is a palindrome, otherwise false.
     */
    public static boolean isPalindrome(int number) {
        if (number < 0) {
            return false;
        }

        int value = number;
        int reverse = 0;
        while (value != 0) {
            int remainder = value % BASE_10;
            reverse = reverse * BASE_10 + remainder;
            value /= BASE_10;
        }

        return number == reverse;
    }
}
