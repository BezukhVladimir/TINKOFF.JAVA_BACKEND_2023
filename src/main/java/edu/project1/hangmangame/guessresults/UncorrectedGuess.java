package edu.project1.hangmangame.guessresults;

import org.jetbrains.annotations.NotNull;

public record UncorrectedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
    @Override
    public @NotNull String message() {
        return "Uncorrected input!\n"
             + "Try to guess a letter again.";
    }
}
