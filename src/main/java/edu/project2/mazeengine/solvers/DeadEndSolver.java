package edu.project2.mazeengine.solvers;

import edu.project2.mazeengine.models.Cell;
import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static edu.project2.mazeengine.utils.Utils.checkPath;
import static edu.project2.mazeengine.utils.Utils.getFromGrid;
import static edu.project2.mazeengine.utils.Utils.setInGrid;

public class DeadEndSolver implements Solver {
    private final static List<Coordinate> DIRECTIONS = Arrays.asList(
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
        Color[][] colorGrid = new Color[maze.size().getHeight()][maze.size().getWidth()];

        for (Cell[] row : maze.grid()) {
            for (Cell cell : row) {
                if (cell.getType() == Cell.Type.WALL) {
                    setColor(colorGrid, cell.getCoordinate(), Color.WALL);
                } else if (cell.getType() == Cell.Type.PASSAGE) {
                    setColor(colorGrid, cell.getCoordinate(), Color.PASSAGE);
                }
            }
        }

        return colorGrid;
    }

    private void updateProtectedColor(Color[][] colorGrid, Coordinate... coordinates) {
        for (Coordinate current : coordinates) {
            setColor(colorGrid, current, Color.PROTECTED);
        }
    }

    private List<Coordinate> getDeadEnds(Color[][] colorGrid, Maze.Size size) {
        List<Coordinate> deadEnds = new ArrayList<>();

        for (int row = 1; row < size.getHeight() - 1; ++row) {
            for (int col = 1; col < size.getWidth() - 1; ++col) {
                Coordinate current = new Coordinate(row, col);

                if (isDeadEnd(colorGrid, current) && !hasColor(colorGrid, current, Color.PROTECTED)) {
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
                if (hasColor(colorGrid, deadEnd, Color.PROTECTED)) {
                    break;
                }

                setColor(colorGrid, deadEnd, Color.DEAD_END);

                deadEnd = getNextPassage(colorGrid, deadEnd);
            }
        }

    }

    private List<Coordinate> getPath(Color[][] colorGrid, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();

        path.add(start);
        Coordinate current = start;
        do {
            setColor(colorGrid, current, Color.PATH);
            current = getNextPassage(colorGrid, current);

            if (current == null) {
                throw new IllegalStateException("Maze cannot be solved!");
            }

            path.add(current);
        } while (!current.equals(end));

        return path;
    }

    private Coordinate getNextPassage(Color[][] colorGrid, Coordinate previous) {
        Coordinate next = null;

        for (Coordinate direction : DIRECTIONS) {
            Coordinate adjacent = previous.add(direction);

            if (hasColor(colorGrid, adjacent, Color.PASSAGE) || hasColor(colorGrid, adjacent, Color.PROTECTED)) {
                next = adjacent;
            }
        }

        return next;
    }

    private boolean isJunction(Color[][] colorGrid, Coordinate coordinate) {
        int counter = 0;

        for (Coordinate direction : DIRECTIONS) {
            Coordinate adjacent = coordinate.add(direction);

            if (hasColor(colorGrid, adjacent, Color.PASSAGE) || hasColor(colorGrid, adjacent, Color.PROTECTED)) {
                ++counter;
            }
        }

        return counter > 1;
    }

    private boolean isDeadEnd(Color[][] colorGrid, Coordinate coordinate) {
        int wallCounter = 0;

        for (Coordinate direction : DIRECTIONS) {
            Coordinate adjacent = coordinate.add(direction);

            if (hasColor(colorGrid, adjacent, Color.WALL)) {
                ++wallCounter;
            }
        }

        return wallCounter == WALLS_IN_DEAD_END;
    }

    private boolean hasColor(Color[][] colorGrid, Coordinate current, Color color) {
        return getFromGrid(colorGrid, current) == color;
    }

    private void setColor(Color[][] colorGrid, Coordinate current, Color color) {
        setInGrid(colorGrid, current, color);
    }
}
