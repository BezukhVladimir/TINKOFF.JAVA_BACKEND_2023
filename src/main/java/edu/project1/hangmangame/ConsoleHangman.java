package edu.project1.hangmangame;

import edu.project1.dictionaries.Dictionary;
import edu.project1.hangmangame.guessresults.GuessResult;
import java.util.Locale;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleHangman {
    public static final String GIVE_UP_COMMAND = "exit";
    private static final String SEPARATOR_LINE = "=================================";

    private final Dictionary wordDictionary;
    private final int maxAttempts;
    private Session session;

    public ConsoleHangman(Dictionary wordDictionary, int maxAttempts) {
        if (maxAttempts < 1) {
            throw new IllegalArgumentException("Maximum attempts must be at least 1.");
        }

        this.wordDictionary = wordDictionary;
        this.maxAttempts = maxAttempts;
    }

    public void run() {
        printWelcomeMessage();
        initNewSession();
        startGameSession();
        printGameOverMessage();
    }

    public void initNewSession() {
        String currentAnswer = wordDictionary.getRandomWord().toLowerCase();
        session = new Session(currentAnswer, maxAttempts);
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

    private void startGameSession() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!session.isGameFinished()) {
                printGuessPrompt();

                String playerInput = getPlayerInput(scanner);
                GuessResult guessResult = tryGuess(playerInput);

                printGameState(guessResult);
            }
        }
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
        return input.equalsIgnoreCase(GIVE_UP_COMMAND);
    }

    private boolean isUncorrectedGuess(@NotNull String input) {
        return !input.matches("\\pL");
    }

    private void printWelcomeMessage() {
        System.out.println();
        System.out.println(
                """
                =================================
                ┏┳┓┓┏┏┓ ┓┏┏┓┳┓┏┓┳┳┓┏┓┳┓ ┏┓┏┓┳┳┓┏┓
                 ┃ ┣┫┣  ┣┫┣┫┃┃┃┓┃┃┃┣┫┃┃ ┃┓┣┫┃┃┃┣\s
                 ┻ ┛┗┗┛ ┛┗┛┗┛┗┗┛┛ ┗┛┗┛┗ ┗┛┛┗┛ ┗┗┛
                ======= CREATED BY BEZUKH =======
                """);
        System.out.printf("You have %d attempts.%n", maxAttempts);
        System.out.printf("To exit the game, enter: %s%n", GIVE_UP_COMMAND);
        System.out.println(SEPARATOR_LINE);
    }

    private void printGuessPrompt() {
        System.out.println();
        System.out.println(SEPARATOR_LINE);
        System.out.print("Guess a letter: ");
    }

    private void printGameState(@NotNull GuessResult guess) {
        System.out.println(guess.message());
        System.out.println("The word: " + String.valueOf(guess.state()));
        System.out.println(SEPARATOR_LINE);
    }

    private void printGameOverMessage() {
        System.out.println();
        System.out.println(SEPARATOR_LINE);
        System.out.println(
                """
                Thanks for playing! See you soon.

                        |\\      _,,,---,,_      \s
                  ZZZzz /,`.-'`'    -.  ;-;;,_  \s
                       |,4-  ) )-,_. ,\\ (  `'-' \s
                      '---''(_/--'  `-'\\_)      \s""");
        System.out.println(SEPARATOR_LINE);
    }
}
