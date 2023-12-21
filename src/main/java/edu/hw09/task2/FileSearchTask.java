package edu.hw09.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSearchTask extends RecursiveTask<List<Path>> {
    private final Path root;
    private final long minFileSize;
    private final long maxFileSize;
    private final String extension;

    public FileSearchTask(Path root, long minFileSize, long maxFileSize, String extension) {
        this.root = root;
        this.minFileSize = minFileSize;
        this.maxFileSize = maxFileSize;
        this.extension = extension;
    }

    @Override
    protected List<Path> compute() {
        // get contents of the current (root) directory
        List<Path> filesAndDirectories;
        try (Stream<Path> stream = Files.list(root)) {
            filesAndDirectories = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // save the files and the subdirectories
        List<Path> files = new ArrayList<>();
        List<Path> subdirectories = new ArrayList<>();
        for (Path content : filesAndDirectories) {
            if (Files.isRegularFile(content)) {
                files.add(content);
            } else if (Files.isDirectory(content)) {
                subdirectories.add(content);
            }
        }

        List<Path> requiredFiles = new ArrayList<>();
        for (Path file : files) {
            long fileSize;
            try {
                fileSize = Files.size(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (fileSize >= minFileSize
             && fileSize <= maxFileSize
             && file.getFileName().toString().endsWith(extension)
            ) {
                requiredFiles.add(file);
            }
        }

        // performing a recursive task for each subdirectory
        List<FileSearchTask> tasks = new ArrayList<>();
        for (Path subdirectory : subdirectories) {
            var task = new FileSearchTask(subdirectory, minFileSize, maxFileSize, extension);
            task.fork();
            tasks.add(task);
        }

        // required files that are included in a subdirectory are included in the current (root) directory
        for (var task : tasks) {
            requiredFiles.addAll(task.join());
        }

        return requiredFiles;
    }
}
