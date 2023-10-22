package edu.hw1.task6;

import static edu.hw1.task2.NumberUtils.countDigits;
import static edu.hw1.task6.ArrayUtils.reverse;

public final class NumberUtils {

    private NumberUtils() {
    }

    public static final int BASE_10 = 10;

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
