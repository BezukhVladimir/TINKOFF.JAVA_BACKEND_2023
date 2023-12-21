package edu.hw03.task8;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BackwardIteratorTest {
    @Test
    void testBackwardlyIteration() {
        // Arrange
        List<Integer> iterableList = List.of(1, 2, 3);
        BackwardIterator<Integer> iterator = new BackwardIterator<>(iterableList);

        // Act
        List<Integer> iterations = new ArrayList<>();
        while (iterator.hasNext()) {
            iterations.add(iterator.next());
        }

        // Assert
        assertThat(iterations).isEqualTo(List.of(3, 2, 1));
    }
}
