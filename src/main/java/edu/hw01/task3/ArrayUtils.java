package edu.hw01.task3;

import java.util.Arrays;

public final class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * Checks if the first array can be nested inside the second array.
     *
     * An array can be nested if:
     * - The minimum value in arr1 is greater than the minimum value in arr2.
     * - The maximum value in arr1 is less than the maximum value in arr2.
     *
     * @param arr1 The first integer array.
     * @param arr2 The second integer array.
     * @return true if arr1 can be nested inside arr2, false otherwise.
     */
    public static boolean isNestable(int[] arr1, int[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) {
            return false;
        }

        int min1 = Arrays.stream(arr1).min().getAsInt();
        int max1 = Arrays.stream(arr1).max().getAsInt();

        int min2 = Arrays.stream(arr2).min().getAsInt();
        int max2 = Arrays.stream(arr2).max().getAsInt();

        return min1 > min2 && max1 < max2;
    }
}
