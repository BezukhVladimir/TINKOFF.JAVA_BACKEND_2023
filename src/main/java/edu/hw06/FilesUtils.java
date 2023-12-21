package edu.hw06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class FilesUtils {
    private FilesUtils() {
    }

    public static boolean fileExists(Path filePath) {
        return Files.exists(filePath);
    }

    public static boolean directoryExists(Path directoryPath) {
        return Files.exists(directoryPath);
    }

    public static void createFile(Path filePath) {
        if (fileExists(filePath)) {
            return;
        }

        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDirectory(Path directoryPath) {
        if (directoryExists(directoryPath)) {
            return;
        }

        try {
            Files.createDirectory(directoryPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(Path filePath) {
        if (!fileExists(filePath)) {
            return;
        }

        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDirectory(Path directoryPath) {
        if (!directoryExists(directoryPath)) {
            return;
        }

        try {
            Files.delete(directoryPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearFile(Path filePath) {
        if (!fileExists(filePath)) {
            return;
        }

        deleteFile(filePath);
        createFile(filePath);
    }

    public static String getFirstLineFromFile(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeStringToEndOfFile(Path filePath, String string) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
            writer.write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cloneFile(Path filePath) {
        if (!fileExists(filePath)) {
            return;
        }

        final String fileNameSplitterRegexPattern = "\\.";
        final String suffixOfCopy = " — копия";

        Path directory = filePath.getParent();

        String[] fileName = filePath.getFileName().toString().split(fileNameSplitterRegexPattern);
        String baseName = fileName[0];
        String extension = fileName[1];

        int copyNumber = 1;
        String copyFileName = baseName + suffixOfCopy + "." + extension;
        Path copyFilePath = directory.resolve(copyFileName);

        while (Files.exists(copyFilePath)) {
            copyNumber++;
            copyFileName = baseName + suffixOfCopy + " (" + copyNumber + ")." + extension;
            copyFilePath = directory.resolve(copyFileName);
        }

        try {
            Files.copy(filePath, copyFilePath);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
