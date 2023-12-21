package edu.hw09.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectorySearchTask extends RecursiveTask<AbstractMap.SimpleEntry<List<Path>, Integer>> {
    private final Path root;
    private final int requiredFilesNumber;

    public DirectorySearchTask(Path root, int requiredFilesNumber) {
        this.root = root;
        this.requiredFilesNumber = requiredFilesNumber;
    }

    @Override
    protected AbstractMap.SimpleEntry<List<Path>, Integer> compute() {
        // get contents of the current (root) directory
        List<Path> filesAndDirectories;
        try (Stream<Path> stream = Files.list(root)) {
            filesAndDirectories = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // count the files and save the subdirectories
        int filesNumber = 0;
        List<Path> subdirectories = new ArrayList<>();
        for (Path content : filesAndDirectories) {
            if (Files.isRegularFile(content)) {
                ++filesNumber;
            } else if (Files.isDirectory(content)) {
                subdirectories.add(content);
            }
        }

        // performing a recursive task for each subdirectory
        List<DirectorySearchTask> tasks = new ArrayList<>();
        for (Path subdirectory : subdirectories) {
            var task = new DirectorySearchTask(subdirectory, requiredFilesNumber);
            task.fork();
            tasks.add(task);
        }

        // files that are included in a subdirectory are included in the current (root) directory
        List<Path> directoriesWithMoreThanRequiredFilesNumber = new ArrayList<>();
        for (var task : tasks) {
            AbstractMap.SimpleEntry<List<Path>, Integer> result = task.join();

            directoriesWithMoreThanRequiredFilesNumber.addAll(result.getKey());
            filesNumber += result.getValue();
        }

        if (filesNumber > requiredFilesNumber) {
            directoriesWithMoreThanRequiredFilesNumber.add(root);
        }

        return new AbstractMap.SimpleEntry<>(
            directoriesWithMoreThanRequiredFilesNumber,
            filesNumber
        );
    }
}
