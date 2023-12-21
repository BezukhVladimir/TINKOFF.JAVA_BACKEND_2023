package edu.hw06.task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static edu.hw06.FilesUtils.createDirectory;
import static edu.hw06.FilesUtils.createFile;
import static edu.hw06.FilesUtils.deleteFile;
import static org.assertj.core.api.Assertions.assertThat;

class GlobMatchesFilterTest {
    private static final String TEST_DIRECTORY = "src/test/java/edu/hw06/task3/test";
    private static final String TEST_FILE1 = "testFile1.txt";
    private static final String TEST_FILE2 = "testFile2.xtt";
    private static final String TEST_FILE3 = "testFile3.md";
    private static final String TXT_PATTERN = "*.txt";

    private Path testDirectoryPath;
    private Path testFile1Path;
    private Path testFile2Path;
    private Path testFile3Path;

    @BeforeEach
    void setUp() {
        testDirectoryPath = Path.of(TEST_DIRECTORY);
        testFile1Path = Paths.get(TEST_DIRECTORY, TEST_FILE1);
        testFile2Path = Paths.get(TEST_DIRECTORY, TEST_FILE2);
        testFile3Path = Paths.get(TEST_DIRECTORY, TEST_FILE3);

        createDirectory(testDirectoryPath);
        createFile(testFile1Path);
        createFile(testFile2Path);
        createFile(testFile3Path);
    }

    @AfterEach
    void tearDown() {
        deleteFile(testFile1Path);
        deleteFile(testFile2Path);
        deleteFile(testFile3Path);
        deleteFile(testDirectoryPath);
    }

    @Test
    void globMatchesFilter() throws IOException {
        // Arrange
        DirectoryStream.Filter<Path> filter
            = GlobMatchesFilter.globMatches(TXT_PATTERN);
        String expected = TEST_FILE1;

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
