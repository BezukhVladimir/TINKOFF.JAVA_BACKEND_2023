package edu.project2.mazeengine.generators;

import edu.project2.mazeengine.models.Cell;
import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static edu.project2.mazeengine.utils.Utils.expandForBorders;
import static edu.project2.mazeengine.utils.Utils.expandForWalls;
import static edu.project2.mazeengine.utils.Utils.getCell;
import static edu.project2.mazeengine.utils.Utils.getFromGrid;
import static edu.project2.mazeengine.utils.Utils.getRandomCoordinate;
import static edu.project2.mazeengine.utils.Utils.getVisited;
import static edu.project2.mazeengine.utils.Utils.isInsideMaze;
import static edu.project2.mazeengine.utils.Utils.isMazeBorder;
import static edu.project2.mazeengine.utils.Utils.removeWall;
import static edu.project2.mazeengine.utils.Utils.setInGrid;

public class DFSGenerator implements Generator {
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

    public Cell[][] getDefaultGrid(Maze.Size size) {
        Cell[][] grid = new Cell[size.getHeight()][size.getWidth()];

        for (int row = 0; row < size.getHeight(); ++row) {
            for (int col = 0; col < size.getWidth(); ++col) {
                Coordinate current = new Coordinate(row, col);

                if (isMazeBorder(size, current)) {
                    setInGrid(grid, current, getCell(current, Cell.Type.WALL));
                } else if (isCellBorder(current)) {
                    setInGrid(grid, current, getCell(current, Cell.Type.WALL));
                } else {
                    setInGrid(grid, current, getCell(current, Cell.Type.PASSAGE));
                }
            }
        }

        return grid;
    }

    public boolean isCellBorder(Coordinate coordinate) {
        return (coordinate.getRow() % 2 == 0) || (coordinate.getCol() % 2 == 0);
    }

    private void generateMaze(Cell[][] grid, Maze.Size size) {
        Boolean[][] visited = getVisited(size);
        Coordinate start = grid[1][1].getCoordinate();
        dfs(grid, size, visited, start);
    }

    private void dfs(Cell[][] grid, Maze.Size size, Boolean[][] visited, Coordinate current) {
        setInGrid(visited, current, true);

        List<Coordinate> adjacentCoordinates = getAdjacentCoordinates(grid, size, current);
        while (!adjacentCoordinates.isEmpty()) {
            Coordinate adjacent = getRandomCoordinate(adjacentCoordinates);

            if (!getFromGrid(visited, adjacent)) {
                Coordinate removed = removeWall(grid, size, current, adjacent);
                setInGrid(visited, removed, true);
                dfs(grid, size, visited, adjacent);
            }

            adjacentCoordinates.remove(adjacent);
        }
    }

    private List<Coordinate> getAdjacentCoordinates(Cell[][] grid, Maze.Size size, Coordinate coordinate) {
        List<Coordinate> adjacentCoordinates = new ArrayList<>(DIRECTIONS.size());

        for (Coordinate direction : DIRECTIONS) {
            Coordinate currentAdjacent = coordinate.add(direction);

            if (!isInsideMaze(size, currentAdjacent)) {
                continue;
            }

            adjacentCoordinates.add(currentAdjacent);
        }

        return adjacentCoordinates;
    }
}
