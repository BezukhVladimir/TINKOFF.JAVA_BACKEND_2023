package edu.hw9.task2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static edu.hw6.FilesUtils.createDirectory;
import static edu.hw6.FilesUtils.createFile;
import static edu.hw6.FilesUtils.deleteDirectory;
import static edu.hw6.FilesUtils.deleteFile;
import static edu.hw6.FilesUtils.writeStringToEndOfFile;
import static edu.hw9.task2.SearchUtils.findDirectories;
import static edu.hw9.task2.SearchUtils.findFiles;
import static org.assertj.core.api.Assertions.assertThat;

final class SearchUtilsTest {
    private static final Path root = Paths.get("src/test/java/edu/hw9/task2/");

    @BeforeAll
    static void createContent() {
        createDirectory(root.resolve("test/"));

        createDirectory(root.resolve("test/test11/"));
        createDirectory(root.resolve("test/test12/"));

        createDirectory(root.resolve("test/test11/test21/"));
        createDirectory(root.resolve("test/test11/test22/"));

        createFile(root.resolve("test/test11/test21.txt"));
        writeStringToEndOfFile(root.resolve("test/test11/test21.txt"), "6BYTES");

        createFile(root.resolve("test/test11/test21/test31.txt"));
        createFile(root.resolve("test/test11/test22/test31.md"));
        writeStringToEndOfFile(root.resolve("test/test11/test22/test31.md"), "6BYTES");

        createFile(root.resolve("test/test12/test21.txt"));
        createFile(root.resolve("test/test12/test22.txt"));
        writeStringToEndOfFile(root.resolve("test/test12/test22.txt"), "7 BYTES");
    }

    @AfterAll
    static void deleteContent() {
        deleteFile(root.resolve("test/test11/test21.txt"));

        deleteFile(root.resolve("test/test11/test21/test31.txt"));
        deleteFile(root.resolve("test/test11/test22/test31.md"));

        deleteFile(root.resolve("test/test12/test21.txt"));
        deleteFile(root.resolve("test/test12/test22.txt"));

        deleteDirectory(root.resolve("test/test11/test21/"));
        deleteDirectory(root.resolve("test/test11/test22/"));

        deleteDirectory(root.resolve("test/test11/"));
        deleteDirectory(root.resolve("test/test12/"));

        deleteDirectory(root.resolve("test/"));
    }

    @Test
    void testDirectorySearch() {
        // Act
        List<Path> result = findDirectories(root.resolve("test/"), 2);

        // Assert
        assertThat(result).hasSize(2);
    }

    @Test
    void testFileSearch() {
        // Act
        List<Path> result = findFiles(root.resolve("test/"), 6, 6, ".txt");

        // Assert
        assertThat(result).hasSize(1);
    }
}
