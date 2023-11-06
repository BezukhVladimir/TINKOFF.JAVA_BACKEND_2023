package edu.project2.mazeengine.models;

import java.util.Objects;

public final class Coordinate {
    int row;
    int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void set(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Coordinate add(Coordinate other) {
        int newRow = this.row + other.row;
        int newCol = this.col + other.col;

        return new Coordinate(newRow, newCol);
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Coordinate that = (Coordinate) obj;

        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
