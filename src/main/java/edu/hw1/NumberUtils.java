package edu.hw1;

import static edu.hw1.ArrayUtils.reverse;

public final class NumberUtils {

    private NumberUtils() {
    }

    public static final int BASE_10 = 10;

    /**
     * Counts the number of digits in a given integer.
     *
     * @param number The integer for which you want to count the digits.
     * @return The count of digits in the integer.
     */
    @Deprecated
    public static int classicCountDigits(int number) {
        if (number == 0) {
            return 1;
        }

        int count = 0;
        int value = number;
        while (value != 0) {
            value /= BASE_10;
            count++;
        }

        return count;
    }

    /**
     * Efficiently calculates the number of digits in the given 32-bit decimal integer number.
     *
     * This method employs comparisons with threshold values.
     * The hierarchy of comparisons is structured in the style of binary search.
     *
     * @param number The 32-bit decimal integer number for which to count the digits.
     * @return The number of digits in the given 32-bit decimal integer number.
     */
    @SuppressWarnings({"MagicNumber", "NestedIfDepth"})
    public static int countDigits(int number) {
        if (number == Integer.MIN_VALUE) {
            return 10;
        }

        if (number < 0) {
            return countDigits(-number);
        }

        int numberOfDigits;
        if (number >= 10_000) {
            if (number >= 10_000_000) {
                if (number >= 100_000_000) {
                    if (number >= 1_000_000_000) {
                        numberOfDigits = 10;
                    } else {
                        numberOfDigits = 9;
                    }
                } else {
                    numberOfDigits = 8;
                }
            } else if (number >= 100_000) {
                if (number >= 1_000_000) {
                    numberOfDigits = 7;
                } else {
                    numberOfDigits = 6;
                }
            } else {
                numberOfDigits = 5;
            }
        } else if (number >= 100) {
            if (number >= 1_000) {
                numberOfDigits = 4;
            } else {
                numberOfDigits = 3;
            }
        } else if (number >= 10) {
            numberOfDigits = 2;
        } else {
            numberOfDigits = 1;
        }

        return numberOfDigits;
    }

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

    /**
     * Checks if all digits in the number are the same.
     *
     * @param number The input number.
     * @return true if all digits are the same, otherwise false.
     */
    public static boolean areAllDigitsSame(int number) {
        int value = number;
        int lastDigit = value % BASE_10;
        while (value != 0) {
            if (value % BASE_10 != lastDigit) {
                return false;
            }

            value /= BASE_10;
        }

        return true;
    }

    /**
     * Returns the digits of the given {@code number}.
     *
     * If the order of the digits is not important, use the more performant {@code getDigitsInReverseOrder()} method.
     *
     * @param number The input number.
     * @return An array containing the digits of the input {@code number}.
     */
    public static int[] getDigits(int number) {
        int[] digits = getDigitsInReverseOrder(number);

        reverse(digits);

        return digits;
    }

    /**
     * Returns the digits of the given {@code number} in reverse order.
     *
     * @param number The input number.
     * @return An array containing the digits of the input {@code number} in reverse order.
     */
    public static int[] getDigitsInReverseOrder(int number) {
        int value = number >= 0 ? number : -number;
        int numberOfDigits = countDigits(number);

        int[] digits = new int[numberOfDigits];
        for (int i = 0; i < numberOfDigits; ++i) {
            digits[i] = value % BASE_10;
            value /= BASE_10;
        }

        return digits;
    }

    /**
     * Returns the last {@code numberOfDigits} digits of the given {@code number} in reverse order.
     *
     * If there are fewer digits in the {@code number} than requested in the {@code numberOfDigits},
     * the remaining elements of the array are filled with zeros
     *
     * @param number The input number.
     * @param numberOfDigits The number of digits to retrieve.
     * @return An array containing the last {@code numberOfDigits} digits of the input {@code number} in reverse order.
     */
    public static int[] getDigitsInReverseOrder(int number, int numberOfDigits) {
        int value = number >= 0 ? number : -number;

        int[] digits = new int[numberOfDigits];
        for (int i = 0; i < numberOfDigits; ++i) {
            digits[i] = value % BASE_10;
            value /= BASE_10;
        }

        return digits;
    }

    /**
     * Forms a number using the digits within the specified range in the array.
     * All digits must be represented by positive numbers.
     *
     * @param digits The array of digits (positive integers).
     * @param startIndex The starting index of the range (inclusive).
     * @param endIndex The ending index of the range (inclusive).
     * @return The number formed using the digits within the specified range in the array.
     */
    public static int getNumberFromDigits(int[] digits, int startIndex, int endIndex) {
        int result = 0;
        for (int i = startIndex; i <= endIndex; ++i) {
            result = result * BASE_10 + digits[i];
        }

        return result;
    }

    /**
     * Forms a number using the digits within the specified range in the array in reverse order.
     * All digits must be represented by positive numbers.
     *
     * @param digits The array of digits (positive integers).
     * @param startIndex The starting index of the range (inclusive).
     * @param endIndex The ending index of the range (inclusive).
     * @return The number formed using the digits within the specified range in the array in reverse order.
     */
    public static int getNumberFromDigitsInReverseOrder(int[] digits, int startIndex, int endIndex) {
        int result = 0;
        for (int i = endIndex; i >= startIndex; --i) {
            result = result * BASE_10 + digits[i];
        }

        return result;
    }
}
