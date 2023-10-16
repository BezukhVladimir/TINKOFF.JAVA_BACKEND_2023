package edu.project1.dictionaries;

import java.util.Locale;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class DemoDictionary implements Dictionary {
    private final Random random = new Random();
    private final String[] words = {
        "no", "point", "in", "dying"
    };

    @Override
    public @NotNull String getRandomWord() {
        return words[random.nextInt(words.length)].toLowerCase(Locale.ROOT);
    }
}
