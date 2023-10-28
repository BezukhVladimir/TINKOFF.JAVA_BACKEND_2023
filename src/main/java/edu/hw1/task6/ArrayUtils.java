package edu.hw1.task6;

public final class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * Reverses the order of elements in an integer array.
     *
     * @param array The integer array to be reversed.
     */
    public static void reverse(int[] array) {
        int endIndex = array.length - 1;
        int halfLength = array.length / 2;
        for (int i = 0; i < halfLength; ++i) {
            int temp = array[i];
            array[i] = array[endIndex - i];
            array[endIndex - i] = temp;
        }
    }
}
