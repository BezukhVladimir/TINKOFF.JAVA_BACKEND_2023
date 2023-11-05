package edu.project2.mazeengine.generators;

import edu.project2.mazeengine.models.Cell;
import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static edu.project2.mazeengine.utils.Utils.expandForBorders;
import static edu.project2.mazeengine.utils.Utils.expandForWalls;
import static edu.project2.mazeengine.utils.Utils.getFromGrid;
import static edu.project2.mazeengine.utils.Utils.getRandomCell;
import static edu.project2.mazeengine.utils.Utils.getRandomCoordinate;
import static edu.project2.mazeengine.utils.Utils.isInsideMaze;
import static edu.project2.mazeengine.utils.Utils.isWall;
import static edu.project2.mazeengine.utils.Utils.removeWall;

public class PrimsGenerator implements Generator {
    private final static List<Coordinate> DIRECTIONS = Arrays.asList(
        new Coordinate(0, -2),
        new Coordinate(-2, 0),
        new Coordinate(0, 2),
        new Coordinate(2, 0)
    );

    @Override
    public Maze generate(int height, int width) {
        Maze.Size size = getMazeSize(height, width);
        Cell[][] grid  = getMazeGrid(size);

        return new Maze(size, grid);
    }

    private Maze.Size getMazeSize(int height, int width) {
        Maze.Size size = new Maze.Size(height, width);

        expandForWalls(size);
        expandForBorders(size);

        return size;
    }

    private Cell[][] getMazeGrid(Maze.Size size) {
        Cell[][] grid = getDefaultGrid(size);
        generateMaze(grid, size);
        return grid;
    }

    private Cell[][] getDefaultGrid(Maze.Size size) {
        Cell[][] grid = new Cell[size.getHeight()][size.getWidth()];

        for (int row = 0; row < size.getHeight(); ++row) {
            for (int col = 0; col < size.getWidth(); ++col) {
                grid[row][col] = new Cell(new Coordinate(row, col), Cell.Type.WALL);
            }
        }

        return grid;
    }

    private void generateMaze(Cell[][] grid, Maze.Size size) {
        Cell current = grid[1][1];
        current.setType(Cell.Type.PASSAGE);

        Set<Cell> walls = getAdjacentWallCells(grid, size, current.getCoordinate());
        while (!walls.isEmpty()) {
            Cell adjacent = getRandomCell(walls);

            walls.remove(adjacent);
            adjacent.setType(Cell.Type.PASSAGE);

            Cell passage = findPassage(grid, size, adjacent.getCoordinate());
            removeWall(grid, size, adjacent.getCoordinate(), passage.getCoordinate());

            walls.addAll(getAdjacentWallCells(grid, size, adjacent.getCoordinate()));
        }
    }

    private Set<Cell> getAdjacentWallCells(Cell[][] grid, Maze.Size size, Coordinate coordinate) {
        Set<Cell> adjacentWalls = new HashSet<>(DIRECTIONS.size());

        for (Coordinate direction : DIRECTIONS) {
            Coordinate adjacent = coordinate.add(direction);

            if (!isInsideMaze(size, adjacent) || !isWall(grid, adjacent)) {
                continue;
            }

            adjacentWalls.add(getFromGrid(grid, adjacent));
        }

        return adjacentWalls;
    }

    private Cell findPassage(Cell[][] grid, Maze.Size size, Coordinate coordinate) {
        List<Coordinate> directions = new ArrayList<>(DIRECTIONS);

        Cell foundPassage = null;
        while (!directions.isEmpty()) {
            Coordinate direction = getRandomCoordinate(directions);
            directions.remove(direction);

            Coordinate adjacent = coordinate.add(direction);

            if (!isInsideMaze(size, adjacent) || isWall(grid, adjacent)) {
                continue;
            }

            foundPassage = getFromGrid(grid, adjacent);
            break;
        }

        return foundPassage;
    }
}
