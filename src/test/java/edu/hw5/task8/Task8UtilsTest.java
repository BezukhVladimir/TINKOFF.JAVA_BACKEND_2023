package edu.hw5.task8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Task8UtilsTest {
    @ParameterizedTest
    @ValueSource(strings = {
        "0", "1",
        "000", "111",
        "00000", "10101"
    })
    public void testIsValidStringForSubtask1(String validString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask1(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "00", "11",
        "0000", "1111"
    })
    public void testIsInvalidStringForSubtask1(String invalidString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask1(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "10", "11",
        "000", "001", "010", "011",
        "1000"
    })
    public void testIsValidStringForSubtask2(String validString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask2(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1",
        "00", "01",
        "100", "101", "110", "111",
        "0000"
    })
    public void testIsInvalidStringForSubtask2(String invalidString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask2(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "", "111",
        "000", "010101", "111000", "000111",
        "010101000"
    })
    public void testIsValidStringForSubtask3(String validString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask3(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0", "00", "0000", "00000"
    })
    public void testIsInvalidStringForSubtask3(String invalidString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask3(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0", "1",
        "00", "01", "10",
        "110", "011",
        "1110", "0111", "1111"
    })
    public void testIsValidStringForSubtask4(String validString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask4(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "11", "111"
    })
    public void testIsInvalidStringForSubtask4(String invalidString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask4(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1",
        "10", "11",
        "101", "111",
        "1010", "1011", "1110", "1111"
    })
    public void testIsValidStringForSubtask5(String validString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask5(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "00", "01",
        "000", "001", "010", "011", "100", "110"
    })
    public void testIsInvalidStringForSubtask5(String invalidString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask5(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "001", "010", "100",
        "0001", "0010",
        "000010000"
    })
    public void testIsValidStringForSubtask6(String validString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask6(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "", "1", "00", "0011", "01"
    })
    public void testIsInvalidStringForSubtask6(String invalidString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask6(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0", "1",
        "00", "01", "10",
        "000", "001", "010", "100", "101"
    })
    public void testIsValidStringForSubtask7(String validString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask7(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "11",
        "011", "110", "111"
    })
    public void testIsInvalidStringForSubtask7(String invalidString) {
        // Act
        boolean result = Task8Utils.isStringForSubtask7(invalidString);

        // Assert
        assertThat(result).isFalse();
    }
}
