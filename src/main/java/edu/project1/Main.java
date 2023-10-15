package edu.project1;

import edu.project1.dictionaries.DemoDictionary;
import edu.project1.hangmangame.ConsoleHangman;

public final class Main {
    private final static int MAX_ATTEMPTS = 5;

    private Main() {
    }

    public static void main(String[] args) {
        ConsoleHangman game = new ConsoleHangman(new DemoDictionary(), MAX_ATTEMPTS);
        game.run();
    }
}
