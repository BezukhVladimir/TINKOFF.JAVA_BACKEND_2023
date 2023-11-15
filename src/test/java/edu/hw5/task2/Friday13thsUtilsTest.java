package edu.hw5.task2;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static edu.hw5.task2.Friday13thsUtils.findAllFriday13thsInYear;
import static edu.hw5.task2.Friday13thsUtils.findNextFriday13th;
import static org.assertj.core.api.Assertions.assertThat;

public final class Friday13thsUtilsTest {
    @Test
    void testFindAllFriday13thsInYear() {
        // Arrange
        int year = 1925;
        var expected = List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );

        // Act
        List<LocalDate> result = findAllFriday13thsInYear(year);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testFindNextFriday13th() {
        // Arrange
        var current  = LocalDate.of(2024, 9, 13);
        var expected = LocalDate.of(2024, 12, 13);

        // Act
        LocalDate result = findNextFriday13th(current);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
