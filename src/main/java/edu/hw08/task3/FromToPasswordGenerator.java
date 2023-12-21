package edu.hw08.task3;

import java.util.NoSuchElementException;

public final class FromToPasswordGenerator {
    private final String alphabet;
    private final int[] indices;
    private final int to;

    /**
     * Constructs a FromToPasswordGenerator with the specified parameters.
     * <p>
     * <p>
     * <p>Example:
     * <p>alphabet = "abc"
     * <p>from = 0, to = 1,
     * <p>passwordLength = 3
     * <p>
     * <p>aaa <- first permutation
     * <p>aab
     * <p>aac
     * <p>aba
     * <p>...
     * <p>bbc
     * <p>bca
     * <p>bcb
     * <p>bcc <- last permutation
     *
     * @param alphabet       The alphabet used to generate passwords.
     * @param from           The starting index for characters in the alphabet.
     * @param to             The ending index for characters in the alphabet.
     * @param passwordLength The desired length of generated passwords.
     */
    FromToPasswordGenerator(String alphabet, int from, int to, int passwordLength) {
        this.alphabet = alphabet;
        indices = new int[passwordLength];
        indices[0] = from;
        this.to = to;
    }

    public String generate() {
        if (!hasNextPassword()) {
            throw new NoSuchElementException("No more passwords available.");
        }

        var password = new StringBuilder();
        for (int index : indices) {
            password.append(alphabet.charAt(index));
        }

        generateNextPassword();
        return password.toString();
    }

    public boolean hasNextPassword() {
        return indices[0] != to + 1;
    }

    private void generateNextPassword() {
        for (int i = indices.length - 1; i >= 0; --i) {
            ++indices[i];

            if (indices[i] == alphabet.length()) {
                if (i == 0) {
                    indices[i] = alphabet.length();
                } else {
                    indices[i] = 0;
                }
            } else {
                break;
            }
        }
    }
}
