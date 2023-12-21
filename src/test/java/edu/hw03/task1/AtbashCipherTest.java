package edu.hw03.task1;

import org.junit.jupiter.api.Test;
import static edu.hw03.task1.AtbashCipherUtils.atbash;
import static org.assertj.core.api.Assertions.assertThat;

class AtbashCipherTest {
    @Test
    void emptyString() {
        // Arrange
        String input = "";

        // Act
        String output = atbash(input);

        // Assert
        assertThat(output).isEqualTo("");
    }

    @Test
    void stringWithLatinCharactersOnly() {
        // Arrange
        String input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String expected = "zyxwvutsrqponmlkjihgfedcbaZYXWVUTSRQPONMLKJIHGFEDCBA";

        // Act
        String output = atbash(input);

        // Assert
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void stringWithNonLatinCharactersOnly() {
        // Arrange
        String input = "(?!<===   ===>!?)";

        // Act
        String output = atbash(input);

        // Assert
        assertThat(output).isEqualTo(input);
    }

    @Test
    void exampleString() {
        // Arrange
        String input =
            "Any fool can write code that a computer can understand. "
                + "Good programmers write code that humans can understand. "
                + "― Martin Fowler";
        String expected =
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. "
                + "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. "
                + "― Nzigrm Uldovi";

        // Act
        String output = atbash(input);

        // Assert
        assertThat(output).isEqualTo(expected);
    }
}
