package edu.hw06.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw06.FilesUtils.cloneFile;
import static edu.hw06.FilesUtils.createDirectory;
import static edu.hw06.FilesUtils.createFile;
import static edu.hw06.FilesUtils.deleteDirectory;
import static edu.hw06.FilesUtils.deleteFile;
import static edu.hw06.FilesUtils.fileExists;
import static org.assertj.core.api.Assertions.assertThat;

class CloneFileTest {
    private static final String TEST_DIRECTORY = "src/test/java/edu/hw06/task2/test";
    private static final String TEST_FILE = "testFile.txt";
    private static final String TEST_FILE_FIRST_COPY = "testFile — копия.txt";
    private static final String TEST_FILE_SECOND_COPY = "testFile — копия (2).txt";
    private static final String TEST_FILE_COPY_OF_SECOND_COPY = "testFile — копия (2) — копия.txt";

    private Path testDirectoryPath;
    private Path testFilePath;
    private Path testFileFirstCopyPath;
    private Path testFileSecondCopyPath;
    private Path testFileCopyOfSecondCopyPath;

    @BeforeEach
    void setUp() {
        testDirectoryPath = Path.of(TEST_DIRECTORY);
        createDirectory(testDirectoryPath);

        testFilePath = Paths.get(TEST_DIRECTORY, TEST_FILE);
        createFile(testFilePath);

        testFileFirstCopyPath        = Paths.get(TEST_DIRECTORY, TEST_FILE_FIRST_COPY);
        testFileSecondCopyPath       = Paths.get(TEST_DIRECTORY, TEST_FILE_SECOND_COPY);
        testFileCopyOfSecondCopyPath = Paths.get(TEST_DIRECTORY, TEST_FILE_COPY_OF_SECOND_COPY);
    }

    @AfterEach
    void tearDown() {
        deleteFile(testFilePath);
        deleteFile(testFileFirstCopyPath);
        deleteFile(testFileSecondCopyPath);
        deleteFile(testFileCopyOfSecondCopyPath);
        deleteDirectory(testDirectoryPath);
    }

    @Test
    void cloneFileFirstCopy() {
        // Act
        cloneFile(testFilePath);

        // Assert
        assertThat(fileExists(testFileFirstCopyPath)).isTrue();
    }

    @Test
    void cloneFileSecondCopy() {
        // Arrange
        cloneFile(testFilePath);

        // Act
        cloneFile(testFilePath);

        // Assert
        assertThat(fileExists(testFileSecondCopyPath)).isTrue();
    }

    @Test
    void cloneFileCopyOfCopy() {
        // Arrange
        cloneFile(testFilePath);
        cloneFile(testFilePath);

        // Act
        cloneFile(testFileSecondCopyPath);

        // Assert
        assertThat(fileExists(testFileCopyOfSecondCopyPath)).isTrue();
    }
}
