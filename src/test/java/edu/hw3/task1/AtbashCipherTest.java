package edu.hw3.task1;

import org.junit.jupiter.api.Test;
import static edu.hw3.task1.AtbashCipherUtils.atbash;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtbashCipherTest {
    @Test
    void testEmptyString() {
        // Arrange
        String input = "";

        // Act
        String output = atbash(input);

        // Assert
        assertEquals("", output);
    }

    @Test
    void testStringWithLatinCharactersOnly() {
        // Arrange
        String input    = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String expected = "zyxwvutsrqponmlkjihgfedcbaZYXWVUTSRQPONMLKJIHGFEDCBA";

        // Act
        String output = atbash(input);

        // Assert
        assertEquals(expected, output);
    }

    @Test
    void testStringWithNonLatinCharactersOnly() {
        // Arrange
        String input = "(?!<===   ===>!?)";

        // Act
        String output = atbash(input);

        // Assert
        assertEquals(input, output);
    }

    @Test
    void testExampleString() {
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
        assertEquals(expected, output);
    }
}
