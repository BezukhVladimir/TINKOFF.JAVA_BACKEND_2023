package edu.project3.logsprovider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;

public final class LogsProvider {
    private static final String PATH_FROM_CONTENT_ROOT = "src/main/java/edu/project3/";
    private static final Path ROOT_DIRECTORY = Path.of(PATH_FROM_CONTENT_ROOT);

    private LogsProvider() {
    }

    public static List<Path> findLogFilesInRootDirectory(PathMatcher pathMatcher) {
        try (var stream = Files.walk(ROOT_DIRECTORY)) {
            return stream
                .filter(pathMatcher::matches)
                .toList();
        } catch (IOException ignored) {
            return List.of();
        }
    }

    public static List<String> readAllLinesFrom(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                List<String> lines = new ArrayList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

                return lines;
            }
        } catch (IOException ignored) {
            return null;
        }
    }
}
