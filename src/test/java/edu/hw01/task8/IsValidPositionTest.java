package edu.hw01.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class IsValidPositionTest {
    @Test
    @DisplayName("Valid position within the chessboard boundaries")
    void testValidPosition() {
        // Arrange
        int row = 2;
        int col = 1;
        int numberOfRows = 8;
        int numberOfCols = 8;

        // Act
        boolean isValid = ChessboardUtils.isValidPosition(row, col, numberOfRows, numberOfCols);

        // Assert
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("Invalid position with negative row")
    void testInvalidRow() {
        // Arrange
        int row = -1;
        int col = 3;
        int numberOfRows = 8;
        int numberOfCols = 8;

        // Act
        boolean isValid = ChessboardUtils.isValidPosition(row, col, numberOfRows, numberOfCols);

        // Assert
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("Invalid position with row greater than number of rows")
    void testRowGreaterThanNumberOfRows() {
        // Arrange
        int row = 8;
        int col = 3;
        int numberOfRows = 8;
        int numberOfCols = 8;

        // Act
        boolean isValid = ChessboardUtils.isValidPosition(row, col, numberOfRows, numberOfCols);

        // Assert
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("Invalid position with negative column")
    void testInvalidCol() {
        // Arrange
        int row = 2;
        int col = -2;
        int numberOfRows = 8;
        int numberOfCols = 8;

        // Act
        boolean isValid = ChessboardUtils.isValidPosition(row, col, numberOfRows, numberOfCols);

        // Assert
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("Invalid position with column greater than number of columns")
    void testColGreaterThanNumberOfCols() {
        // Arrange
        int row = 2;
        int col = 8;
        int numberOfRows = 8;
        int numberOfCols = 8;

        // Act
        boolean isValid = ChessboardUtils.isValidPosition(row, col, numberOfRows, numberOfCols);

        // Assert
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("Valid position at the top-left corner of the chessboard")
    void testTopLeftCorner() {
        // Arrange
        int row = 0;
        int col = 0;
        int numberOfRows = 8;
        int numberOfCols = 8;

        // Act
        boolean isValid = ChessboardUtils.isValidPosition(row, col, numberOfRows, numberOfCols);

        // Assert
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("Valid position at the bottom-right corner of the chessboard")
    void testBottomRightCorner() {
        // Arrange
        int row = 7;
        int col = 7;
        int numberOfRows = 8;
        int numberOfCols = 8;

        // Act
        boolean isValid = ChessboardUtils.isValidPosition(row, col, numberOfRows, numberOfCols);

        // Assert
        assertThat(isValid).isTrue();
    }
}
