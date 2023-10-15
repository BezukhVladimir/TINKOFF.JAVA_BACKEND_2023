package edu.project1.hangmangame.guessresults;

import org.jetbrains.annotations.NotNull;

public record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
    @Override
    public @NotNull String message() {
        return String.format("Missed, mistake %d out of %d.", attempt, maxAttempts);
    }
}
