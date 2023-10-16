package edu.project1.hangmangame;

import edu.project1.dictionaries.Dictionary;
import edu.project1.hangmangame.session.SessionManager;
import edu.project1.hangmangame.settings.SettingsManager;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleHangman {
    private final Dictionary wordDictionary;
    private final int maxAttempts;
    private final SettingsManager settings;

    public ConsoleHangman(Dictionary wordDictionary, int maxAttempts) {
        if (maxAttempts < 1) {
            throw new IllegalArgumentException("Maximum attempts must be at least 1.");
        }

        this.wordDictionary = wordDictionary;
        this.maxAttempts = maxAttempts;
        this.settings = SettingsManager.getInstance();
    }

    public void run() {
        printWelcomeMessage();
        launchGameSession();
        printGameOverMessage();
    }

    private void launchGameSession() {
        var sessionManager = new SessionManager(wordDictionary, maxAttempts);
        sessionManager.launchGameSession();
    }

    private void printWelcomeMessage() {
        System.out.println();
        System.out.println(settings.get("GAME_LOGO_MESSAGE"));
        System.out.printf(settings.get("ATTEMPTS_MESSAGE"), maxAttempts);
        System.out.printf(settings.get("GIVE_UP_COMMAND_HINT_MESSAGE"), settings.get("GIVE_UP_COMMAND"));
        System.out.println(settings.get("SEPARATOR_LINE"));
    }

    private void printGameOverMessage() {
        System.out.println();
        System.out.println(settings.get("SEPARATOR_LINE"));
        System.out.println(settings.get("GAME_OVER_MESSAGE"));
        System.out.println(settings.get("SEPARATOR_LINE"));
    }
}
