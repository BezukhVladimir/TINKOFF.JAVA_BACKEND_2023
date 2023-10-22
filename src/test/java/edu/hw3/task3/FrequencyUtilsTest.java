package edu.hw3.task3;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static edu.hw3.task3.FrequencyUtils.freqDict;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrequencyUtilsTest {
    @Test
    void testStrings() {
        // Arrange
        List<String> words = List.of("код", "код", "код", "bug");

        // Act
        Map<String, Integer> wordFreq = freqDict(words);

        // Assert
        assertEquals(3, wordFreq.get("код"));
        assertEquals(1, wordFreq.get("bug"));
        assertEquals(2, wordFreq.size());
    }

    @Test
    void testIntegers() {
        // Arrange
        List<Integer> numbers = List.of(1, 1, 2, 2);

        // Act
        Map<Integer, Integer> numberFreq = freqDict(numbers);

        // Assert
        assertEquals(2, numberFreq.get(1));
        assertEquals(2, numberFreq.get(2));
        assertEquals(2, numberFreq.size());
    }
}
