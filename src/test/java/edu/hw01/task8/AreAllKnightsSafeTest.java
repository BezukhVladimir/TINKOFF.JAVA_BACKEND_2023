package edu.hw01.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AreAllKnightsSafeTest {
    @Test
    @DisplayName("Knights are safe on the board")
    void knightsAreSafe1() {
        // Arrange
        int[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        // Act
        boolean result = ChessboardUtils.areAllKnightsSafe(board);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Knights are safe on the board")
    void knightsAreSafe2() {
        // Arrange
        int[][] board = {
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1}
        };

        // Act
        boolean result = ChessboardUtils.areAllKnightsSafe(board);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Knights are not safe on the board")
    void knightsAreNotSafe1() {
        // Arrange
        int[][] board = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        // Act
        boolean result = ChessboardUtils.areAllKnightsSafe(board);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Knights are not safe on the board")
    void knightsAreNotSafe2() {
        // Arrange
        int[][] board = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };

        // Act
        boolean result = ChessboardUtils.areAllKnightsSafe(board);

        // Assert
        assertThat(result).isFalse();
    }
}
