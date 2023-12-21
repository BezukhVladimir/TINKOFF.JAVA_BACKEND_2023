package edu.hw03.task2;

import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw03.task2.ClusterUtils.clusterize;
import static org.assertj.core.api.Assertions.assertThat;

public class ClusterTest {
    @Test
    void testSingleClusters() {
        // Arrange
        String input = "()()()";

        // Act
        List<String> results = clusterize(input);

        // Assert
        for (String result : results) {
            assertThat(result).isEqualTo("()");
        }
    }

    @Test
    void testBigCluster() {
        // Arrange
        String input = "((()))";

        // Act
        List<String> results = clusterize(input);

        // Assert
        assertThat(results)
            .hasSize(1)
            .containsExactly("((()))");
    }

    @Test
    void testExampleString1() {
        // Arrange
        String input = "((()))(())()()(()())";

        // Act
        List<String> results = clusterize(input);

        // Assert
        assertThat(results)
            .hasSize(5)
            .containsExactly("((()))", "(())", "()", "()", "(()())");
    }

    @Test
    void testExampleString2() {
        // Arrange
        String input = "((())())(()(()()))";

        // Act
        List<String> results = clusterize(input);

        // Assert
        assertThat(results)
            .hasSize(2)
            .containsExactly("((())())", "(()(()()))");
    }
}
