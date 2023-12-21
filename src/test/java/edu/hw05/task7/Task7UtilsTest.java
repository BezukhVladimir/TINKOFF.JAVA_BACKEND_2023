package edu.hw05.task7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;

public class Task7UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "000", "010",
        "100", "110",
        "11011"
    })
    public void testIsValidStringForSubtask1(String validString) {
        // Act
        boolean result = Task7Utils.isStringForSubtask1(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "001", "011",
        "101", "111",
        "11111", "00"
    })
    public void testIsInvalidStringForSubtask1(String invalidString) {
        // Act
        boolean result = Task7Utils.isStringForSubtask1(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "101", "010",
        "111", "000"
    })
    public void testIsValidStringForSubtask2(String validString) {
        // Act
        boolean result = Task7Utils.isStringForSubtask2(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "001", "110",
        "011", "100"
    })
    public void testIsInvalidStringForSubtask2(String invalidString) {
        // Act
        boolean result = Task7Utils.isStringForSubtask2(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0", "1",
        "00", "01", "10", "11",
        "000", "001", "010", "011",
        "100", "101", "110", "111"
    })
    public void testIsValidStringForSubtask3(String validString) {
        // Act
        boolean result = Task7Utils.isStringForSubtask3(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "", "1111"
    })
    public void testIsInvalidStringForSubtask3(String invalidString) {
        // Act
        boolean result = Task7Utils.isStringForSubtask3(invalidString);

        // Assert
        assertThat(result).isFalse();
    }
}
