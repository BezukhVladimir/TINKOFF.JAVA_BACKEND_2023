package edu.hw06.task3;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

class MagicNumberFilterTest {
    private static final String TEST_DIRECTORY = "src/test/java/edu/hw06/task3";
    private static final String TEST_PNG_FILE = "cat.png";
    private static final byte[] PNG_FILE_SIGNATURE = {(byte) 0x89, 0x50, 0x4E, 0x47};

    private Path testDirectoryPath = Path.of(TEST_DIRECTORY);;

    @Test
    void magicNumberFilter() throws IOException {
        // Arrange
        DirectoryStream.Filter<Path> filter
            = MagicNumberFilter.magicNumber(PNG_FILE_SIGNATURE);
        String expected = TEST_PNG_FILE;

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
