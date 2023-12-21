package edu.hw03.task7;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MyNullComparatorTest {
    @Test
    void isNull() {
        // Arrange
        TreeMap<String, String> tree = new TreeMap<>(
            new MyNullComparator<String>()
        );

        // Act
        tree.put(null, "test");

        // Assert
        assertThat(tree.containsKey(null)).isTrue();
    }
}
