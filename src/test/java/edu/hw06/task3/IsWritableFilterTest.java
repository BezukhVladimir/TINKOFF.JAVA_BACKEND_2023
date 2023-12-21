package edu.hw06.task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw06.FilesUtils.createDirectory;
import static edu.hw06.FilesUtils.createFile;
import static edu.hw06.FilesUtils.deleteDirectory;
import static edu.hw06.FilesUtils.deleteFile;

public class IsWritableFilterTest {
    private static final String TEST_DIRECTORY = "src/test/java/edu/hw06/task3/test";
    private static final String TEST_WRITABLE_FILE = "testWritableFile.txt";
    private static final String TEST_UNWRITABLE_FILE = "testUnwritableFile.txt";

    private Path testDirectoryPath;
    private Path testWritableFilePath;
    private Path testUnwritableFilePath;

    @BeforeEach
    void setUp() {
        testDirectoryPath      = Path.of(TEST_DIRECTORY);
        testWritableFilePath   = Paths.get(TEST_DIRECTORY, TEST_WRITABLE_FILE);
        testUnwritableFilePath = Paths.get(TEST_DIRECTORY, TEST_UNWRITABLE_FILE);

        createDirectory(testDirectoryPath);
        createFile(testWritableFilePath);
        createFile(testUnwritableFilePath);

        // TODO
        // Для тестирования этого фильтра нужно научиться создавать writable и unwritable файлы
    }

    @AfterEach
    void tearDown() {
        deleteFile(testWritableFilePath);
        deleteFile(testUnwritableFilePath);
        deleteDirectory(testDirectoryPath);
    }
}
