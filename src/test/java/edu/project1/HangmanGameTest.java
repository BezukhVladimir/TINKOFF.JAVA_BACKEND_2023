package edu.project1;

import edu.project1.dictionaries.Dictionary;
import java.util.ArrayList;
import java.util.List;
import edu.project1.hangmangame.ConsoleHangman;
import edu.project1.hangmangame.guessresults.DefeatGuess;
import edu.project1.hangmangame.guessresults.FailedGuess;
import edu.project1.hangmangame.guessresults.GuessResult;
import edu.project1.hangmangame.guessresults.RepeatedGuess;
import edu.project1.hangmangame.guessresults.SuccessfulGuess;
import edu.project1.hangmangame.guessresults.UncorrectedGuess;
import edu.project1.hangmangame.guessresults.WinGuess;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class HangmanGameTest {
    private static class TestDictionary implements Dictionary {
        @Override
        public @NotNull String getRandomWord() {
            return "hangman";
        }
    }

    private static final String ANSWER = "hangman";
    private static final String CORRECT_GUESSES = "aghmn";
    private static final String WRONG_GUESSES = "bcdefijklopqrstuvwxyz";
    private static final int MAX_ATTEMPTS = 5;
    private final ConsoleHangman testGame = new ConsoleHangman(new TestDictionary(), MAX_ATTEMPTS);

    @BeforeEach
    void initNewSession() {
        testGame.initNewSession();
    }

    @Test
    @DisplayName("Test when player wins")
    void testPlayerWon() {
        List<String> inputs = new ArrayList<>();
        for (char correctGuess : CORRECT_GUESSES.toCharArray()) {
            inputs.add(String.valueOf(correctGuess));
        }

        for (int i = 0; i < inputs.size() - 1; ++i) {
            GuessResult result = testGame.tryGuess(inputs.get(i));
            assertThat(result).isInstanceOf(SuccessfulGuess.class);
        }

        GuessResult result = testGame.tryGuess(inputs.get(inputs.size() - 1));
        assertThat(result).isInstanceOf(WinGuess.class);
    }

    @Test
    @DisplayName("Test when player is defeated")
    void testPlayerDefeated() {
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < MAX_ATTEMPTS; ++i) {
            char wrongGuess = WRONG_GUESSES.charAt(i);
            inputs.add(String.valueOf(wrongGuess));
        }

        for (int i = 0; i < MAX_ATTEMPTS - 1; ++i) {
            GuessResult result = testGame.tryGuess(inputs.get(i));
            assertThat(result).isInstanceOf(FailedGuess.class);
        }

        GuessResult result = testGame.tryGuess(inputs.get(inputs.size() - 1));
        assertThat(result).isInstanceOf(DefeatGuess.class);
    }

    @Test
    @DisplayName("Test when player gives up")
    void testGiveUpCommand() {
        String input = ConsoleHangman.GIVE_UP_COMMAND;

        GuessResult result = testGame.tryGuess(input);

        assertThat(result).isInstanceOf(DefeatGuess.class);
    }

    @Test
    @DisplayName("Test repeated input")
    void testRepeatedInput() {
        String input = String.valueOf(ANSWER.charAt(0));

        GuessResult result = testGame.tryGuess(input);
        assertThat(result).isInstanceOf(SuccessfulGuess.class);

        for (int i = 0; i < 10; ++i) {
            GuessResult resultRepeat = testGame.tryGuess(input);
            assertThat(resultRepeat).isInstanceOf(RepeatedGuess.class);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "*", "1", "12", "aa", "AA" })
    @DisplayName("Test uncorrected input")
    void testUncorrectedInput(String input) {
        GuessResult result = testGame.tryGuess(input);

        assertThat(result).isInstanceOf(UncorrectedGuess.class);
    }
}
