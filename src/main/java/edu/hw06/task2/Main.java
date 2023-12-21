package edu.hw06.task2;

import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw06.FilesUtils.cloneFile;
import static edu.hw06.FilesUtils.createFile;

public final class Main {
    private Main() {
    }

    private final static String TOP_SECRET_DIRECTORY = "src/main/java/edu/hw6/task2";
    private final static String TOP_SECRET_FILE = "Tinkoff Bank Biggest Secret.txt";

    public static void main(String[] args) {
        Path biggestSecret = Paths.get(TOP_SECRET_DIRECTORY, TOP_SECRET_FILE);

        createFile(biggestSecret);

        cloneFile(biggestSecret);
        cloneFile(biggestSecret);
        cloneFile(biggestSecret);
    }
}
