package edu.hw08.task3;

import java.util.NoSuchElementException;

public final class PasswordGenerator {
    private final String alphabet;
    private final int[] indices;
    private long passwordCount;

    public PasswordGenerator(String alphabet, int passwordLength) {
        this.alphabet = alphabet;
        indices = new int[passwordLength];
        passwordCount = (long) Math.pow(this.alphabet.length(), passwordLength);
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
        return passwordCount != 0;
    }

    private void generateNextPassword() {
        --passwordCount;

        for (int i = indices.length - 1; i >= 0; --i) {
            ++indices[i];

            if (indices[i] == alphabet.length()) {
                indices[i] = 0;
            } else {
                break;
            }
        }
    }
}
