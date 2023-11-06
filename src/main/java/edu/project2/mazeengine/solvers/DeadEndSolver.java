package edu.project2.mazeengine.solvers;

import edu.project2.mazeengine.models.Cell;
import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import java.util.ArrayList;
import java.util.List;
import static edu.project2.mazeengine.utils.Utils.checkPath;
import static edu.project2.mazeengine.utils.Utils.createColorGrid;
import static edu.project2.mazeengine.utils.Utils.createCoordinate;
import static edu.project2.mazeengine.utils.Utils.getFromGrid;
import static edu.project2.mazeengine.utils.Utils.isPassage;
import static edu.project2.mazeengine.utils.Utils.isWall;
import static edu.project2.mazeengine.utils.Utils.setInGrid;

public class DeadEndSolver implements Solver {
    private final static List<Coordinate> DIRECTIONS = List.of(
        new Coordinate(0, -1),
        new Coordinate(-1, 0),
        new Coordinate(0, 1),
        new Coordinate(1, 0)
    );
    private final static int WALLS_IN_DEAD_END = DIRECTIONS.size() - 1;

    public enum Color {
        WALL,
        PASSAGE,
        PROTECTED,
        DEAD_END,
        PATH
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        checkPath(maze, start, end);

        Color[][] colorGrid = getDefaultColorGrid(maze);

        updateProtectedColor(colorGrid, start, end);

        List<Coordinate> deadEnds = getDeadEnds(colorGrid, maze.size());
        updateDeadEndsColor(colorGrid, deadEnds);

        return getPath(colorGrid, start, end);
    }

    private Color[][] getDefaultColorGrid(Maze maze) {
        Color[][] colorGrid = createColorGrid(maze.size());

        for (Cell[] row : maze.grid()) {
            for (Cell cell : row) {
                if (isWall(cell)) {
                    setWallColor(colorGrid, cell.getCoordinate());
                } else if (isPassage(cell)) {
                    setPassageColor(colorGrid, cell.getCoordinate());
                }
            }
        }

        return colorGrid;
    }

    private void updateProtectedColor(Color[][] colorGrid, Coordinate... coordinates) {
        for (Coordinate current : coordinates) {
            setProtectedColor(colorGrid, current);
        }
    }

    private List<Coordinate> getDeadEnds(Color[][] colorGrid, Maze.Size size) {
        List<Coordinate> deadEnds = new ArrayList<>();

        for (int row = 1; row < size.getHeight() - 1; ++row) {
            for (int col = 1; col < size.getWidth() - 1; ++col) {
                Coordinate current = createCoordinate(row, col);

                if (isDeadEnd(colorGrid, current) && !hasProtectedColor(colorGrid, current)) {
                    deadEnds.add(current);
                }
            }
        }

        return deadEnds;
    }

    private void updateDeadEndsColor(Color[][] colorGrid, List<Coordinate> deadEnds) {
        for (int i = 0; i < deadEnds.size(); ++i) {
            Coordinate deadEnd = deadEnds.get(i);

            while (deadEnd != null && !isJunction(colorGrid, deadEnd)) {
                if (hasProtectedColor(colorGrid, deadEnd)) {
                    break;
                }

                setDeadEndColor(colorGrid, deadEnd);

                deadEnd = getNextPassage(colorGrid, deadEnd);
            }
        }
    }

    private List<Coordinate> getPath(Color[][] colorGrid, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();

        path.add(start);
        Coordinate current = start;
        do {
            setPathColor(colorGrid, current);
            current = getNextPassage(colorGrid, current);

            if (current == null) {
                throw new IllegalStateException("Maze cannot be solved!");
            }

            path.add(current);
        } while (!current.equals(end));

        return path;
    }

    private Coordinate getNextPassage(Color[][] colorGrid, Coordinate previous) {
        return DIRECTIONS.stream()
            .map(previous::add)
            .filter(adjacent -> hasPassageColor(colorGrid, adjacent) || hasProtectedColor(colorGrid, adjacent))
            .findFirst()
            .orElse(null);
    }

    private boolean isJunction(Color[][] colorGrid, Coordinate coordinate) {
        long counter = DIRECTIONS.stream()
            .map(coordinate::add)
            .filter(adjacent -> hasPassageColor(colorGrid, adjacent) || hasProtectedColor(colorGrid, adjacent))
            .count();

        return counter > 1;
    }

    private boolean isDeadEnd(Color[][] colorGrid, Coordinate coordinate) {
        long wallCounter = DIRECTIONS.stream()
            .map(coordinate::add)
            .filter(adjacent -> hasWallColor(colorGrid, adjacent))
            .count();

        return wallCounter == WALLS_IN_DEAD_END;
    }

    private boolean hasColor(Color[][] colorGrid, Coordinate current, Color color) {
        return getFromGrid(colorGrid, current) == color;
    }

    private boolean hasWallColor(Color[][] colorGrid, Coordinate current) {
        return hasColor(colorGrid, current, Color.WALL);
    }

    private boolean hasPassageColor(Color[][] colorGrid, Coordinate current) {
        return hasColor(colorGrid, current, Color.PASSAGE);
    }

    private boolean hasProtectedColor(Color[][] colorGrid, Coordinate current) {
        return hasColor(colorGrid, current, Color.PROTECTED);
    }

    private void setColor(Color[][] colorGrid, Coordinate current, Color color) {
        setInGrid(colorGrid, current, color);
    }

    private void setPathColor(Color[][] colorGrid, Coordinate current) {
        setInGrid(colorGrid, current, Color.PATH);
    }

    private void setDeadEndColor(Color[][] colorGrid, Coordinate current) {
        setInGrid(colorGrid, current, Color.DEAD_END);
    }

    private void setProtectedColor(Color[][] colorGrid, Coordinate current) {
        setInGrid(colorGrid, current, Color.PROTECTED);
    }

    private void setWallColor(Color[][] colorGrid, Coordinate current) {
        setInGrid(colorGrid, current, Color.WALL);
    }

    private void setPassageColor(Color[][] colorGrid, Coordinate current) {
        setInGrid(colorGrid, current, Color.PASSAGE);
    }
}
