package edu.hw06.task4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw06.FilesUtils.createFile;
import static edu.hw06.FilesUtils.deleteFile;
import static edu.hw06.FilesUtils.getFirstLineFromFile;
import static edu.hw06.task4.OutputStreamCompositionManager.write;
import static org.assertj.core.api.Assertions.assertThat;

class OutputStreamCompositionManagerTest {
    private static final String TEST_DIRECTORY = "src/test/java/edu/hw06/task4";
    private static final String TEST_FILE = "testFile.txt";

    private Path testFilePath;

    @BeforeEach
    void setUp() {
        testFilePath = Paths.get(TEST_DIRECTORY, TEST_FILE);
        createFile(testFilePath);
    }

    @AfterEach
    void tearDown() {
        deleteFile(testFilePath);
    }

    @Test
    void testWrite() throws IOException {
        // Arrange
        String expected = "Programming is learned by writing programs. ― Brian Kernighan";

        // Act
        write(testFilePath.toString(), expected);
        String result = getFirstLineFromFile(testFilePath);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
