package edu.project1.hangmangame.session;

import edu.project1.dictionaries.Dictionary;
import edu.project1.hangmangame.guessresults.GuessResult;
import edu.project1.hangmangame.settings.SettingsManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Locale;
import java.util.Scanner;

public class SessionManager {
    private final SettingsManager settings;
    private Session session;

    public SessionManager(Dictionary wordDictionary, int maxAttempts) {
        String currentAnswer = wordDictionary.getRandomWord().toLowerCase();
        this.session = new Session(currentAnswer, maxAttempts);
        this.settings = SettingsManager.getInstance();
    }

    public void launchGameSession() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!session.isGameFinished()) {
                printGuessPrompt();

                String playerInput = getPlayerInput(scanner);
                GuessResult guessResult = tryGuess(playerInput);

                printGameState(guessResult);
            }
        }
    }

    public GuessResult tryGuess(String input) {
        if (isInvalidInput(input)) {
            return session.uncorrectedGuess();
        }

        if (isGiveUpCommand(input)) {
            return session.giveUp();
        }

        if (isUncorrectedGuess(input)) {
            return session.uncorrectedGuess();
        }

        return session.guess(input.toLowerCase(Locale.ROOT).charAt(0));
    }

    private @Nullable String getPlayerInput(@NotNull Scanner scanner) {
        String input = scanner.hasNext() ? scanner.next() : null;
        System.out.println();

        return input;
    }

    private boolean isInvalidInput(String input) {
        return input == null
            || input.isEmpty();
    }

    private boolean isGiveUpCommand(@NotNull String input) {
        return input.equalsIgnoreCase(settings.get("GIVE_UP_COMMAND"));
    }

    private boolean isUncorrectedGuess(@NotNull String input) {
        return !input.matches(settings.get("CORRECT_INPUT_REGEX"));
    }

    private void printGuessPrompt() {
        System.out.println();
        System.out.println(settings.get("SEPARATOR_LINE"));
        System.out.print(settings.get("GUESS_PROMPT_MESSAGE"));
    }

    private void printGameState(@NotNull GuessResult guess) {
        System.out.println(guess.message());
        System.out.println("The word: " + String.valueOf(guess.state()));
        System.out.println(settings.get("SEPARATOR_LINE"));
    }
}
