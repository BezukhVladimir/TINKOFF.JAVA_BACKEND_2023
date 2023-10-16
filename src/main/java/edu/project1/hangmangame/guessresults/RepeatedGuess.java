package edu.project1.hangmangame.guessresults;

import edu.project1.hangmangame.settings.SettingsManager;
import org.jetbrains.annotations.NotNull;

public record RepeatedGuess(
    char[] state,
    int attempt,
    int maxAttempts
) implements GuessResult {
    @Override
    public @NotNull String message() {
        var settings = SettingsManager.getInstance();
        return settings.get("REPEATED_MESSAGE");
    }
}
