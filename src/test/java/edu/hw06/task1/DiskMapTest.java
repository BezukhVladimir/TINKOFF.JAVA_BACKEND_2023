package edu.hw06.task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw06.FilesUtils.deleteDirectory;
import static edu.hw06.FilesUtils.deleteFile;
import static edu.hw06.FilesUtils.getFirstLineFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public final class DiskMapTest {
    private static final String TEST_DIRECTORY = "src/test/java/edu/hw06/task1/test";
    private static final String TEST_FILE = "testFile.txt";
    private static final String SPLITTER = ":";

    private Path testDirectoryPath;
    private Path testFilePath;
    private DiskMap diskMap;

    @BeforeEach
    void setUp() {
        testDirectoryPath = Path.of(TEST_DIRECTORY);
        testFilePath = Paths.get(TEST_DIRECTORY, TEST_FILE);
        diskMap = new DiskMap(TEST_DIRECTORY, TEST_FILE);
    }

    @AfterEach
    void tearDown() {
        deleteFile(testFilePath);
        deleteDirectory(testDirectoryPath);
    }

    @Test
    void testPut() {
        // Arrange
        String key = "hello";
        String value = "world";
        String expected = key + SPLITTER + value;

        // Act
        diskMap.put(key, value);
        String result = getFirstLineFromFile(testFilePath);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testRemove() {
        // Arrange
        String key = "hello";
        String value = "world";
        diskMap.put(key, value);

        // Act
        String removedValue = diskMap.remove(key);
        String result = getFirstLineFromFile(testFilePath);

        // Assert
        assertThat(removedValue).isEqualTo(value);
        assertThat(result).isNull();
    }
}
