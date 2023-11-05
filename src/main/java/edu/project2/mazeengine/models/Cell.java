package edu.project2.mazeengine.models;

import java.util.Objects;

public final class Cell {
    Coordinate coordinate;
    Type type;

    public Cell(Coordinate coordinate, Type type) {
        this.coordinate = coordinate;
        this.type       = type;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void set(Coordinate coordinate, Cell.Type type) {
        this.coordinate = coordinate;
        this.type       = type;
    }

    public void set(int row, int col, Cell.Type type) {
        this.coordinate.setRow(row);
        this.coordinate.setCol(col);
        this.type = type;
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Cell cell = (Cell) obj;

        return Objects.equals(coordinate, cell.coordinate) && type == cell.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, type);
    }

    public enum Type {
        WALL, PASSAGE
    }
}
