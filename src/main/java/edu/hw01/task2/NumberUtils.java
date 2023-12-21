package edu.hw01.task2;

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
}
