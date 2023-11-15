package edu.hw5.task4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw5.task4.PasswordValidator.containsSpecialCharacter;
import static org.assertj.core.api.Assertions.assertThat;

public final class PasswordValidatorTest {
    private final static String INVALID_PASSWORD = "don't_peek";

    @ParameterizedTest
    @ValueSource(chars = {
        '~', '!', '@', '#', '$', '%', '^', '&', '*', '|'
    })
    void testValidPassword(char specialCharacter) {
        // Arrange
        String validPassword = INVALID_PASSWORD + specialCharacter;

        // Act
        boolean result = containsSpecialCharacter(validPassword);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    void testInvalidPassword() {
        // Act
        boolean result = containsSpecialCharacter(INVALID_PASSWORD);

        // Assert
        assertThat(result).isFalse();
    }
}
