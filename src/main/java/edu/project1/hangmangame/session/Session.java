package edu.project1.hangmangame.session;

import edu.project1.hangmangame.guessresults.DefeatGuess;
import edu.project1.hangmangame.guessresults.FailedGuess;
import edu.project1.hangmangame.guessresults.GuessResult;
import edu.project1.hangmangame.guessresults.RepeatedGuess;
import edu.project1.hangmangame.guessresults.SuccessfulGuess;
import edu.project1.hangmangame.guessresults.UncorrectedGuess;
import edu.project1.hangmangame.guessresults.WinGuess;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import edu.project1.hangmangame.settings.SettingsManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

class Session {

    private final @NotNull String answer;
    private final int maxAttempts;
    private final char[] playerAnswer;
    private int attempts;

    private final SettingsManager settings;
    private final Set<Character> usedLetters;
    private int numberOfHiddenLetters;
    private boolean isGameFinished;


    Session(@NotNull String answer, int maxAttempts) {
        this.answer = answer;
        this.maxAttempts = maxAttempts;

        attempts = 0;
        playerAnswer = new char[answer.length()];
        settings = SettingsManager.getInstance();
        Arrays.fill(this.playerAnswer, settings.get("HIDDEN_LETTER_FILLER").charAt(0));

        usedLetters = new HashSet<>();
        numberOfHiddenLetters = answer.length();
        isGameFinished = false;
    }

    @NotNull GuessResult guess(char guess) {
        if (isUsedLetter(guess)) {
            return new RepeatedGuess(playerAnswer, attempts, maxAttempts);
        }

        boolean isSuccessfulPlayerGuess = handlePlayerGuess(guess);

        return isSuccessfulPlayerGuess
            ? handleSuccessfulPlayerGuess()
            : handleFailedPlayerGuess();
    }

    @NotNull GuessResult giveUp() {
        return handlePlayerDefeated();
    }

    @NotNull GuessResult uncorrectedGuess() {
        return new UncorrectedGuess(playerAnswer, attempts, maxAttempts);
    }

    boolean isGameFinished() {
        return isGameFinished;
    }

    private boolean isMaxAttemptsExceeded() {
        return attempts >= maxAttempts;
    }

    private boolean isUsedLetter(char guess) {
        return usedLetters.contains(guess);
    }

    private boolean isAnswerFullyGuessed() {
        return numberOfHiddenLetters == 0;
    }

    private boolean handlePlayerGuess(char guess) {
        usedLetters.add(guess);
        boolean isSuccessfulGuess = false;

        for (int i = 0; i < answer.length(); ++i) {
            if (guess == answer.charAt(i)) {
                playerAnswer[i] = guess;
                --numberOfHiddenLetters;

                isSuccessfulGuess = true;
            }
        }

        return isSuccessfulGuess;
    }

    @Contract(" -> new")
    private @NotNull GuessResult handleSuccessfulPlayerGuess() {
        return isAnswerFullyGuessed()
            ? handlePlayerWon()
            : new SuccessfulGuess(playerAnswer, attempts, maxAttempts);
    }

    @Contract(" -> new")
    private @NotNull GuessResult handleFailedPlayerGuess() {
        ++attempts;

        return isMaxAttemptsExceeded()
            ? handlePlayerDefeated()
            : new FailedGuess(playerAnswer, attempts, maxAttempts);
    }

    @Contract(" -> new")
    private @NotNull GuessResult handlePlayerDefeated() {
        isGameFinished = true;
        return new DefeatGuess(playerAnswer, attempts, maxAttempts);
    }

    @Contract(" -> new")
    private @NotNull GuessResult handlePlayerWon() {
        isGameFinished = true;
        return new WinGuess(playerAnswer, attempts, maxAttempts);
    }
}
