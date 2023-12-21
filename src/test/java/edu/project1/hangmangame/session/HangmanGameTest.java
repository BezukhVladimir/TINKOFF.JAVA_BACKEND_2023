package edu.project1.hangmangame.session;

import edu.project1.dictionaries.Dictionary;
import edu.project1.hangmangame.guessresults.DefeatGuess;
import edu.project1.hangmangame.guessresults.FailedGuess;
import edu.project1.hangmangame.guessresults.GuessResult;
import edu.project1.hangmangame.guessresults.RepeatedGuess;
import edu.project1.hangmangame.guessresults.SuccessfulGuess;
import edu.project1.hangmangame.guessresults.UncorrectedGuess;
import edu.project1.hangmangame.guessresults.WinGuess;
import edu.project1.hangmangame.settings.SettingsManager;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class HangmanGameTest {
    private static final String ANSWER = "hangman";
    private static final String CORRECT_GUESSES = "aghmn";
    private static final String WRONG_GUESSES = "bcdefijklopqrstuvwxyz";
    private static final int MAX_ATTEMPTS = 5;

    private SessionManager testSession;
    @Mock
    private Dictionary dictionaryMock;
    private SettingsManager settings;

    @BeforeEach
    void initNewSession() {
        MockitoAnnotations.openMocks(this);
        when(dictionaryMock.getRandomWord()).thenReturn(ANSWER);
        testSession = new SessionManager(dictionaryMock, MAX_ATTEMPTS);
        settings = SettingsManager.getInstance();
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
            GuessResult result = testSession.tryGuess(inputs.get(i));

            // Assert
            assertThat(result).isInstanceOf(SuccessfulGuess.class);
        }

        // Act
        GuessResult result = testSession.tryGuess(inputs.get(inputs.size() - 1));

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
            GuessResult result = testSession.tryGuess(inputs.get(i));

            // Assert
            assertThat(result).isInstanceOf(FailedGuess.class);
        }

        // Act
        GuessResult result = testSession.tryGuess(inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(DefeatGuess.class);
    }

    @Test
    @DisplayName("Test when player gives up")
    void testGiveUpCommand() {
        // Arrange
        String input = settings.get("GIVE_UP_COMMAND");

        // Act
        GuessResult result = testSession.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(DefeatGuess.class);
    }

    @Test
    @DisplayName("Test repeated input")
    void testRepeatedInput() {
        // Arrange
        String input = String.valueOf(ANSWER.charAt(0));

        // Act
        GuessResult result = testSession.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(SuccessfulGuess.class);

        for (int i = 0; i < 10; ++i) {
            // Act
            GuessResult resultRepeat = testSession.tryGuess(input);

            // Assert
            assertThat(resultRepeat).isInstanceOf(RepeatedGuess.class);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "*", "1", "12", "aa", "AA"})
    @DisplayName("Test uncorrected input")
    void testUncorrectedInput(String input) {
        // Act
        GuessResult result = testSession.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(UncorrectedGuess.class);
    }
}
