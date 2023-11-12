package edu.hw6.task3;

import edu.hw6.FilesUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static edu.hw6.FilesUtils.createDirectory;
import static edu.hw6.FilesUtils.createFile;
import static edu.hw6.FilesUtils.deleteDirectory;
import static edu.hw6.FilesUtils.deleteFile;
import static org.assertj.core.api.Assertions.assertThat;

public class LargerThanFilterTest {
    private static final String TEST_DIRECTORY = "src/test/java/edu/hw6/task3/test";
    private static final String TEST_EMPTY_FILE = "emptyFile.txt";
    private static final String TEST_SMALL_FILE = "smallFile.txt";

    private Path testDirectoryPath;
    private Path testEmptyFilePath;
    private Path testSmallFilePath;

    @BeforeEach
    void setUp() {
        testDirectoryPath = Path.of(TEST_DIRECTORY);
        testEmptyFilePath = Paths.get(TEST_DIRECTORY, TEST_EMPTY_FILE);
        testSmallFilePath = Paths.get(TEST_DIRECTORY, TEST_SMALL_FILE);

        createDirectory(testDirectoryPath);
        createFile(testEmptyFilePath);
        createFile(testSmallFilePath);

        FilesUtils.writeStringToEndOfFile(testSmallFilePath, TEST_SMALL_FILE);
    }

    @AfterEach
    void tearDown() {
        deleteFile(testEmptyFilePath);
        deleteFile(testSmallFilePath);
        deleteDirectory(testDirectoryPath);
    }

    @Test
    void testLargerThanFilter() throws IOException {
        // Arrange
        long size = 0L;
        DirectoryStream.Filter<Path> filter
            = LargerThanFilter.largerThan(size);
        String expected = TEST_SMALL_FILE;

        // Act
        var result = new ArrayList<String>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(testDirectoryPath, filter)) {
            entries.forEach(filePath ->
                result.add(filePath.getFileName().toString())
            );
        }

        // Assert
        assertThat(result).containsExactly(expected);
    }
}
