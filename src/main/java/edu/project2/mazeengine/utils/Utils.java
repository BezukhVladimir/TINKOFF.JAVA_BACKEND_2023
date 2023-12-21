package edu.project2.mazeengine.utils;

import edu.project2.mazeengine.models.Cell;
import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import edu.project2.mazeengine.solvers.DeadEndSolver;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class Utils {
    private static final Random RANDOMIZER = new Random();

    private Utils() {
    }

    public static void expandForWalls(Maze.Size size) {
        if (size.getHeight() > 1) {
            size.setHeight(size.getHeight() + size.getHeight() - 1);
        }

        if (size.getWidth() > 1) {
            size.setWidth(size.getWidth() + size.getWidth() - 1);
        }
    }

    public static void expandForBorders(Maze.Size size) {
        size.setHeight(size.getHeight() + 2);
        size.setWidth(size.getWidth() + 2);
    }

    public static Coordinate getRandomCoordinate(List<Coordinate> coordinates) {
        int randomId = RANDOMIZER.nextInt(coordinates.size());
        return coordinates.get(randomId);
    }

    public static Cell getRandomCell(Set<Cell> coordinates) {
        int randomId = RANDOMIZER.nextInt(coordinates.size());

        Iterator<Cell> iterator = coordinates.iterator();
        Cell randomCell = null;

        for (int i = 0; i <= randomId; ++i) {
            randomCell = iterator.next();
        }

        return randomCell;
    }

    public static boolean isMazeBorder(Maze.Size size, Coordinate coordinate) {
        return coordinate.getRow() == 0
            || coordinate.getCol() == 0
            || coordinate.getRow() == size.getHeight() - 1
            || coordinate.getCol() == size.getWidth() - 1;
    }

    public static boolean isInsideMaze(Maze.Size size, Coordinate coordinate) {
        return coordinate.getRow() > 0
            && coordinate.getCol() > 0
            && coordinate.getRow() < size.getHeight() - 1
            && coordinate.getCol() < size.getWidth() - 1;
    }

    public static Coordinate removeWall(Cell[][] grid, Maze.Size size, Coordinate current, Coordinate adjacent) {
        int wallRow;
        int wallCol;

        if (current.getRow() == adjacent.getRow()) {
            wallRow = current.getRow();
            wallCol = (current.getCol() + adjacent.getCol()) / 2;
        } else {
            wallRow = (current.getRow() + adjacent.getRow()) / 2;
            wallCol = current.getCol();
        }

        grid[wallRow][wallCol].setType(Cell.Type.PASSAGE);

        return grid[wallRow][wallCol].getCoordinate();
    }

    public static void checkPath(Maze maze, Coordinate start, Coordinate end) {
        if (!isInsideMaze(maze.size(), start)) {
            throw new IllegalArgumentException("The path should start inside the maze.");
        }

        if (!isInsideMaze(maze.size(), end)) {
            throw new IllegalArgumentException("The path should end inside the maze.");
        }

        if (isWall(maze.grid(), start)) {
            throw new IllegalArgumentException("The path should start in the passage.");
        }

        if (isWall(maze.grid(), end)) {
            throw new IllegalArgumentException("The path should end in the passage.");
        }
    }

    public static boolean isWall(Cell[][] grid, Coordinate coordinate) {
        return getFromGrid(grid, coordinate).getType() == Cell.Type.WALL;
    }

    public static boolean isWall(Cell cell) {
        return cell.getType() == Cell.Type.WALL;
    }

    public static boolean isPassage(Cell[][] grid, Coordinate coordinate) {
        return getFromGrid(grid, coordinate).getType() == Cell.Type.PASSAGE;
    }

    public static boolean isPassage(Cell cell) {
        return cell.getType() == Cell.Type.PASSAGE;
    }

    public static Cell createCell(Coordinate coordinate, Cell.Type type) {
        return new Cell(coordinate, type);
    }

    public static Cell createWallCell(Coordinate coordinate) {
        return new Cell(coordinate, Cell.Type.WALL);
    }

    public static Cell createPassageCell(Coordinate coordinate) {
        return new Cell(coordinate, Cell.Type.PASSAGE);
    }

    public static <T> T getFromGrid(T[][] grid, Coordinate coordinate) {
        return grid[coordinate.getRow()][coordinate.getCol()];
    }

    public static <T> void setInGrid(T[][] grid, Coordinate coordinate, T object) {
        grid[coordinate.getRow()][coordinate.getCol()] = object;
    }

    public static Boolean[][] createBooleanGrid(Maze.Size size) {
        Boolean[][] grid = new Boolean[size.getHeight()][size.getWidth()];

        for (Boolean[] booleans : grid) {
            Arrays.fill(booleans, false);
        }

        return grid;
    }

    public static Cell[][] createCellGrid(Maze.Size size) {
        return new Cell[size.getHeight()][size.getWidth()];
    }

    public static DeadEndSolver.Color[][] createColorGrid(Maze.Size size) {
        return new DeadEndSolver.Color[size.getHeight()][size.getWidth()];
    }

    public static Coordinate createCoordinate(int row, int col) {
        return new Coordinate(row, col);
    }

    public static Maze.Size createMazeSize(int height, int width) {
        return new Maze.Size(height, width);
    }

    public static Maze createMaze(Maze.Size size, Cell[][] grid) {
        return new Maze(size, grid);
    }
}
