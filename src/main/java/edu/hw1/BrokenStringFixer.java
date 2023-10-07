package edu.hw1;

public final class BrokenStringFixer {

    private BrokenStringFixer() {
    }

    /**
     * Fixes a broken string where every pair of characters has been swapped.
     *
     * @param brokenString The string with swapped character pairs.
     * @return The corrected string with character pairs in the correct order.
     */
    public static String fixString(String brokenString) {
        char[] chars = brokenString.toCharArray();

        for (int i = 0; i < chars.length - 1; i += 2) {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
        }

        return new String(chars);
    }
}
