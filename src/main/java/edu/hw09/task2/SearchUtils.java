package edu.hw09.task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public final class SearchUtils {
    private SearchUtils() {
    }

    public static List<Path> findDirectories(Path root, int requiredFilesNumber) {
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(
                new DirectorySearchTask(root, requiredFilesNumber)
            ).getKey();
        }
    }

    public static List<Path> findFiles(Path root, long minFileSize, long maxFileSize, String extension) {
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(
                new FileSearchTask(root, minFileSize, maxFileSize, extension)
            );
        }
    }
}
