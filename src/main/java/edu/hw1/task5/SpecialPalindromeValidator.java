package edu.hw1.task5;

import static edu.hw1.task5.StringUtils.isPalindrome;

/**
 * The SpecialPalindromeValidator class provides
 * methods for validating whether a given number is a special palindrome.
 *
 * A special palindrome is a positive number
 * that is either a palindrome itself or has a descendant that is a palindrome.
 *
 * A descendant is created by summing each pair of adjacent digits.
 * If the number has an odd number of digits, the last digit is summed with zero.
 */
public final class SpecialPalindromeValidator {

    private SpecialPalindromeValidator() {
    }

    /**
     * Validates if the given number is a special palindrome.
     *
     * @param number The number to validate.
     * @return true if the number is a special palindrome, otherwise false.
     */
    public static boolean isSpecialPalindrome(int number) {
        if (number < 0) {
            return false;
        }

        String current = Integer.toString(number);

        if (isPalindrome(current)) {
            return true;
        }

        while (current.length() > 2) {
            current = getSpecialPalindromeDescendant(current);

            if (isPalindrome(current)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Generates the special palindrome descendant of the given number.
     *
     * @param number The number for which to generate the special palindrome descendant.
     * @return The special palindrome descendant of the given number.
     */
    private static String getSpecialPalindromeDescendant(String number) {
        StringBuilder specialPalindromeDescendant = new StringBuilder();

        int halfLength = number.length() / 2;
        for (int i = 0; i < halfLength; ++i) {
            int digit1 = Character.getNumericValue(number.charAt(i));
            int digit2 = Character.getNumericValue(number.charAt(i + 1));

            specialPalindromeDescendant.append(digit1 + digit2);
        }

        if (number.length() % 2 != 0) {
            specialPalindromeDescendant.append(number.charAt(number.length() - 1));
        }

        return specialPalindromeDescendant.toString();
    }
}
