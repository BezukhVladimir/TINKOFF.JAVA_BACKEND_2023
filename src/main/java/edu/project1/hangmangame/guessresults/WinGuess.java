package edu.project1.hangmangame.guessresults;

import org.jetbrains.annotations.NotNull;

public record WinGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
    @Override
    public @NotNull String message() {
        return "You won!";
    }
}
