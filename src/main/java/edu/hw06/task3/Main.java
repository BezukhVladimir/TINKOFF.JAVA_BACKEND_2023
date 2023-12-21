package edu.hw06.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw06.task3.GlobMatchesFilter.globMatches;
import static edu.hw06.task3.LargerThanFilter.largerThan;
import static edu.hw06.task3.MagicNumberFilter.magicNumber;
import static edu.hw06.task3.RegexContainsFilter.regexContains;

@SuppressWarnings({"RegexpSinglelineJava"})
public final class Main {
    private Main() {
    }

    public static void main(String[] args) throws IOException {
        final byte[] PNG_FILE_SIGNATURE = {(byte) 0x89, 0x50, 0x4E, 0x47};
        final String DIRECTORY = "src/test/java/edu/hw6/task3";

        final AbstractFilter regularFile = Files::isRegularFile;
        final AbstractFilter readable = Files::isReadable;

        final long size = 800;
        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(largerThan(size))
            .and(magicNumber(PNG_FILE_SIGNATURE))
            .and(globMatches("*.png"))
            .and(regexContains(".*cat.*"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(DIRECTORY), filter)) {
            System.out.println();
            System.out.println("Files:");
            entries.forEach(System.out::println);
        }
    }
}
