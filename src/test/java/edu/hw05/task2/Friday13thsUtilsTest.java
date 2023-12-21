package edu.hw05.task2;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public final class Friday13thsUtilsTest {
    @Test
    void findAllFriday13thsInYear() {
        // Arrange
        int year = 1925;
        var expected = List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );

        // Act
        List<LocalDate> result = Friday13thsUtils.findAllFriday13thsInYear(year);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void findNextFriday13th() {
        // Arrange
        var current  = LocalDate.of(2024, 9, 13);
        var expected = LocalDate.of(2024, 12, 13);

        // Act
        LocalDate result = Friday13thsUtils.findNextFriday13th(current);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
