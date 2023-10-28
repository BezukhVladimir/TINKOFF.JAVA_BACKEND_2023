package edu.hw1.task8;

public final class ChessboardUtils {

    private ChessboardUtils() {
    }

    public static final int[][] KNIGHT_ATTACK_DIRECTIONS = {
        {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
        {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };

    /**
     * Checks if a given position (row, col) is valid on a chessboard of the specified size.
     *
     * @param row          The row coordinate of the position.
     * @param col          The column coordinate of the position.
     * @param numberOfRows The total number of rows on the chessboard.
     * @param numberOfCols The total number of columns on the chessboard.
     * @return true if the position is valid, otherwise false.
     */
    public static boolean isValidPosition(int row, int col, int numberOfRows, int numberOfCols) {
        return (row >= 0) && (row < numberOfRows) && (col >= 0) && (col < numberOfCols);
    }

    /**
     * Checks if knights are placed on a chessboard in such a way that no knight can capture another.
     *
     * @param board The chessboard represented as a 2D array with 0s for empty cells and 1s for knight positions.
     * @return true if the knights are safe, otherwise false.
     */
    public static boolean areAllKnightsSafe(int[][] board) {
        int numberOfRows = board.length;
        int numberOfCols = board[0].length;

        for (int i = 0; i < numberOfRows; ++i) {
            for (int j = 0; j < numberOfCols; ++j) {
                if (board[i][j] == 1) {
                    for (int[] attackDirection : KNIGHT_ATTACK_DIRECTIONS) {
                        int targetRow = i + attackDirection[0];
                        int targetCol = j + attackDirection[1];

                        if (isValidPosition(targetRow, targetCol, numberOfRows, numberOfCols)) {
                            if (board[targetRow][targetCol] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
