package edu.hw1.task4;

import edu.hw1.task4.BrokenStringFixer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FixStringTest {

    @Test
    @DisplayName("Fix a string with swapped character pairs")
    void testFixString() {
        // Arrange
        String brokenString = "hTsii  s aimex dpus rtni.g";

        // Act
        String result = BrokenStringFixer.fixString(brokenString);

        // Assert
        assertThat(result).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Fix an empty string")
    void testFixEmptyString() {
        // Arrange
        String brokenString = "";

        // Act
        String result = BrokenStringFixer.fixString(brokenString);

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Fix a string with only one character")
    void testFixStringWithOneCharacter() {
        // Arrange
        String brokenString = "A";

        // Act
        String result = BrokenStringFixer.fixString(brokenString);

        // Assert
        assertThat(result).isEqualTo("A");
    }

    @Test
    @DisplayName("Fix a string with odd length")
    void testFixStringWithOddLength() {
        // Arrange
        String brokenString = "12345";

        // Act
        String result = BrokenStringFixer.fixString(brokenString);

        // Assert
        assertThat(result).isEqualTo("21435");
    }

    @Test
    @DisplayName("Fix a string with even length")
    void testFixStringWithEvenLength() {
        // Arrange
        String brokenString = "abcdef";

        // Act
        String result = BrokenStringFixer.fixString(brokenString);

        // Assert
        assertThat(result).isEqualTo("badcfe");
    }
}
