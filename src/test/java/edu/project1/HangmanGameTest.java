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
import org.mockito.Mockito;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class HangmanGameTest {
    private static final String ANSWER = "hangman";
    private static final String CORRECT_GUESSES = "aghmn";
    private static final String WRONG_GUESSES = "bcdefijklopqrstuvwxyz";
    private static final int MAX_ATTEMPTS = 5;

    private ConsoleHangman testGame;
    private Dictionary dictionaryMock;

    @BeforeEach
    void initNewSession() {
        dictionaryMock = Mockito.mock(Dictionary.class);
        when(dictionaryMock.getRandomWord()).thenReturn(ANSWER);
        testGame = new ConsoleHangman(dictionaryMock, 5);
        testGame.initNewSession();
    }

    @Test
    @DisplayName("Test when player wins")
    void testPlayerWon() {
        // Arrange
        List<String> inputs = new ArrayList<>();
        for (char correctGuess : CORRECT_GUESSES.toCharArray()) {
            inputs.add(String.valueOf(correctGuess));
        }

        for (int i = 0; i < inputs.size() - 1; ++i) {
            // Act
            GuessResult result = testGame.tryGuess(inputs.get(i));

            // Assert
            assertThat(result).isInstanceOf(SuccessfulGuess.class);
        }

        // Act
        GuessResult result = testGame.tryGuess(inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(WinGuess.class);
    }

    @Test
    @DisplayName("Test when player is defeated")
    void testPlayerDefeated() {
        // Arrange
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < MAX_ATTEMPTS; ++i) {
            char wrongGuess = WRONG_GUESSES.charAt(i);
            inputs.add(String.valueOf(wrongGuess));
        }

        for (int i = 0; i < MAX_ATTEMPTS - 1; ++i) {
            // Act
            GuessResult result = testGame.tryGuess(inputs.get(i));

            // Assert
            assertThat(result).isInstanceOf(FailedGuess.class);
        }

        // Act
        GuessResult result = testGame.tryGuess(inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(DefeatGuess.class);
    }

    @Test
    @DisplayName("Test when player gives up")
    void testGiveUpCommand() {
        // Arrange
        String input = ConsoleHangman.GIVE_UP_COMMAND;

        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(DefeatGuess.class);
    }

    @Test
    @DisplayName("Test repeated input")
    void testRepeatedInput() {
        // Arrange
        String input = String.valueOf(ANSWER.charAt(0));

        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(SuccessfulGuess.class);

        for (int i = 0; i < 10; ++i) {
            // Act
            GuessResult resultRepeat = testGame.tryGuess(input);

            // Assert
            assertThat(resultRepeat).isInstanceOf(RepeatedGuess.class);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "*", "1", "12", "aa", "AA" })
    @DisplayName("Test uncorrected input")
    void testUncorrectedInput(String input) {
        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(UncorrectedGuess.class);
    }
}
