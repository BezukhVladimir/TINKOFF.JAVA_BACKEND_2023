package edu.hw6.task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw6.FilesUtils.createDirectory;
import static edu.hw6.FilesUtils.createFile;
import static edu.hw6.FilesUtils.deleteFile;

public class IsReadableFilterTest {
    private static final String TEST_DIRECTORY = "src/test/java/edu/hw6/task3/test";
    private static final String TEST_READABLE_FILE = "testReadableFile.txt";
    private static final String TEST_UNREADABLE_FILE = "testUnreadableFile.txt";

    private Path testDirectoryPath;
    private Path testReadableFilePath;
    private Path testUnreadableFilePath;

    @BeforeEach
    void setUp() {
        testDirectoryPath      = Path.of(TEST_DIRECTORY);
        testReadableFilePath   = Paths.get(TEST_DIRECTORY, TEST_READABLE_FILE);
        testUnreadableFilePath = Paths.get(TEST_DIRECTORY, TEST_UNREADABLE_FILE);

        createDirectory(testDirectoryPath);
        createFile(testReadableFilePath);
        createFile(testUnreadableFilePath);

        // TODO
        // Для тестирования этого фильтра нужно научиться создавать readable и unreadable файлы
    }

    @AfterEach
    void tearDown() {
        deleteFile(testReadableFilePath);
        deleteFile(testUnreadableFilePath);
        deleteFile(testDirectoryPath);
    }
}
