package edu.hw03.task3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static edu.hw03.task3.FrequencyUtils.freqDict;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class FrequencyUtilsTest {
    @Test
    void strings() {
        // Arrange
        List<String> words = List.of("код", "код", "код", "bug");

        // Act
        Map<String, Integer> wordFreq = freqDict(words);

        // Assert
        assertThat(wordFreq)
            .hasSize(2)
            .containsOnly(
                entry("код", 3),
                entry("bug", 1)
            );
    }

    @Test
    void integers() {
        // Arrange
        List<Integer> numbers = List.of(1, 1, 2, 2);

        // Act
        Map<Integer, Integer> numberFreq = freqDict(numbers);

        // Assert
        assertThat(numberFreq)
            .hasSize(2)
            .containsOnly(
                entry(1, 2),
                entry(2, 2)
            );
    }
}
