package edu.hw01.task6;

import java.util.Arrays;
import static edu.hw01.task6.NumberUtils.areAllDigitsSame;
import static edu.hw01.task6.NumberUtils.getDigitsInReverseOrder;
import static edu.hw01.task6.NumberUtils.getNumberFromDigits;
import static edu.hw01.task6.NumberUtils.getNumberFromDigitsInReverseOrder;

public final class KaprekarsRoutine {
    private KaprekarsRoutine() {
    }

    private static final int KAPREKAR_CONSTANT = 6174;
    private static final int NUMBER_OF_DIGITS = 4;

    /**
     * Calculates the number of steps required to reach Kaprekar's constant (6174) from the given number.
     *
     * If all digits of the number are the same, it is impossible to reach Kaprekar's constant, and -1 is returned.
     *
     * @param number The input number from which to calculate the steps to reach Kaprekar's constant.
     * @return The number of steps required to reach Kaprekar's constant, or -1 if it's impossible to reach.
     */
    public static int countK(int number) {
        if (areAllDigitsSame(number)) {
            return -1;
        }

        return countKRec(number);
    }

    /**
     * Recursively calculates the number of steps required to reach Kaprekar's constant (6174) from the given number.
     *
     * @param number The input number from which to calculate the steps to reach Kaprekar's constant.
     * @return The number of steps required to reach Kaprekar's constant.
     * @throws StackOverflowError If all digits of the input number are the same.
     */
    private static int countKRec(int number) {
        if (number == KAPREKAR_CONSTANT) {
            return 0;
        }

        try {
            int[] digits = getDigitsInReverseOrder(number, NUMBER_OF_DIGITS);
            Arrays.sort(digits);
            int ascending = getNumberFromDigits(digits, 0, NUMBER_OF_DIGITS - 1);
            int descending = getNumberFromDigitsInReverseOrder(digits, 0, NUMBER_OF_DIGITS - 1);
            int difference = descending - ascending;

            return 1 + countKRec(difference);
        } catch (StackOverflowError e) {
            return -1;
        }
    }
}
