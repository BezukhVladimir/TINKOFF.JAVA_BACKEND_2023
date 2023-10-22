package edu.hw3.task2;

import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw3.task2.ClusterUtils.clusterize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClusterTest {
    @Test
    void testSingleClusters() {
        // Arrange
        String input = "()()()";

        // Act
        List<String> results = clusterize(input);

        // Assert
        for (String result : results) {
            assertEquals("()", result);
        }
    }

    @Test
    void testBigCluster() {
        // Arrange
        String input = "((()))";

        // Act
        List<String> results = clusterize(input);

        // Assert
        assertEquals("((()))", results.get(0));
        assertEquals(1, results.size());
    }

    @Test
    void testExampleString1() {
        // Arrange
        String input = "((()))(())()()(()())";

        // Act
        List<String> results = clusterize(input);

        // Assert
        assertEquals("((()))", results.get(0));
        assertEquals("(())",   results.get(1));
        assertEquals("()",     results.get(2));
        assertEquals("()",     results.get(3));
        assertEquals("(()())", results.get(4));
        assertEquals(5, results.size());
    }

    @Test
    void testExampleString2() {
        // Arrange
        String input = "((())())(()(()()))";

        // Act
        List<String> results = clusterize(input);

        // Assert
        assertEquals("((())())",   results.get(0));
        assertEquals("(()(()()))", results.get(1));
        assertEquals(2, results.size());
    }
}
