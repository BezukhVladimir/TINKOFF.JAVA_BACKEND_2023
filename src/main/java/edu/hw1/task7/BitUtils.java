package edu.hw1.task7;

public final class BitUtils {
    private BitUtils() {
    }

    /**
     * Calculates the number of bits needed to represent the given integer.
     *
     * @param number The integer to calculate the number of bits for.
     * @return The number of bits required to represent the integer.
     */
    public static int getNumberOfBits(int number) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(number);
    }

    /**
     * Performs a left circular shift (rotation) of bits in the given positive integer.
     *
     * @param number The positive integer to perform the left circular shift on.
     * @param shift The number of bits to shift. If a number is negative, shift is reversed.
     * @return The result of the left circular shift operation. If the input {@code number} is negative, -1 is returned.
     */
    public static int rotateLeft(int number, int shift) {
        if (number == 0) {
            return 0;
        }

        if (number < 0) {
            return -1;
        }

        if (shift < 0) {
            return rotateRight(number, -shift);
        }

        int numberOfBits = getNumberOfBits(number);
        int bitShift = shift % numberOfBits;
        int bitMask = (1 << numberOfBits) - 1;

        return ((number << bitShift) | (number >> (numberOfBits - bitShift))) & bitMask;
    }

    /**
     * Performs a right circular shift (rotation) of bits in the given positive integer.
     *
     * @param number The positive integer to perform the right circular shift on.
     * @param shift The number of bits to shift. If a number is negative, shift is reversed.
     * @return The result of a right circular shift operation. If an input {@code number} is negative, -1 is returned.
     */
    public static int rotateRight(int number, int shift) {
        if (number == 0) {
            return 0;
        }

        if (number < 0) {
            return -1;
        }

        if (shift < 0) {
            return rotateLeft(number, -shift);
        }

        int numberOfBits = getNumberOfBits(number);
        int bitShift = shift % numberOfBits;
        int bitMask = (1 << numberOfBits) - 1;

        return ((number >> bitShift) | (number << (numberOfBits - bitShift))) & bitMask;
    }
}
