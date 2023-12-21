package edu.hw06.task1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static edu.hw06.FilesUtils.clearFile;
import static edu.hw06.FilesUtils.createDirectory;
import static edu.hw06.FilesUtils.createFile;
import static edu.hw06.FilesUtils.writeStringToEndOfFile;

public class DiskMap implements Map<String, String> {
    private final static String SPLITTER = ":";

    private final Path directoryPath;
    private final Path filePath;
    private final Map<String, String> cache = new HashMap<>();

    public DiskMap(String directory, String fileName) {
        directoryPath = Paths.get(directory);
        createDirectory(directoryPath);

        filePath = Paths.get(directory, fileName);
        createFile(filePath);

        loadAllFromDiskMapFileToCache();
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return cache.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return cache.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return cache.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String previousValue = cache.put(key, value);

        if (previousValue == null) {
            saveToDiskMapFile(key, value);
        }

        return previousValue;
    }

    @Override
    public String remove(Object key) {
        String removedValue = cache.remove(key);

        if (removedValue != null) {
            saveAllFromCacheToDiskMapFile();
        }

        return removedValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        cache.clear();
        clearFile(filePath);
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return cache.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return cache.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return cache.entrySet();
    }

    private void loadAllFromDiskMapFileToCache() {
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> {
                String[] parts = line.split(SPLITTER);
                cache.put(parts[0], parts[1]);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToDiskMapFile(String key, String value) {
        String entry = createEntryForDiskMapFile(key, value);
        writeStringToEndOfFile(filePath, entry);
    }

    private void saveAllFromCacheToDiskMapFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            cache.forEach((key, value) -> {
                try {
                    String entry = createEntryForDiskMapFile(key, value);
                    writer.write(entry);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String createEntryForDiskMapFile(String key, String value) {
        return key + SPLITTER + value + System.lineSeparator();
    }
}
