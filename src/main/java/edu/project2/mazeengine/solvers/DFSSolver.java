package edu.project2.mazeengine.solvers;

import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import java.util.ArrayList;
import java.util.List;
import static edu.project2.mazeengine.utils.Utils.checkPath;
import static edu.project2.mazeengine.utils.Utils.getFromGrid;
import static edu.project2.mazeengine.utils.Utils.getRandomCoordinate;
import static edu.project2.mazeengine.utils.Utils.createBooleanGrid;
import static edu.project2.mazeengine.utils.Utils.isInsideMaze;
import static edu.project2.mazeengine.utils.Utils.isWall;
import static edu.project2.mazeengine.utils.Utils.setInGrid;

public class DFSSolver implements Solver {
    private final static List<Coordinate> DIRECTIONS = List.of(
        new Coordinate(0, -1),
        new Coordinate(-1, 0),
        new Coordinate(0, 1),
        new Coordinate(1, 0)
    );
    private List<Coordinate> path;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        checkPath(maze, start, end);

        path = new ArrayList<>();
        generatePath(maze, start, end);

        if (!path.contains(end)) {
            throw new IllegalStateException("Maze cannot be solved!");
        }

        return path;
    }

    private void generatePath(Maze maze, Coordinate start, Coordinate end) {
        Boolean[][] visited = createBooleanGrid(maze.size());
        dfs(maze, visited, start, end);
    }

    private boolean dfs(
        Maze maze,
        Boolean[][] visited,
        Coordinate current,
        Coordinate end
    ) {
        if (current.equals(end)) {
            path.add(current);
            return true;
        }

        setInGrid(visited, current, true);

        List<Coordinate> adjacentCoordinates = getAdjacentCoordinates(maze, current);
        while (!adjacentCoordinates.isEmpty()) {
            Coordinate adjacent = getRandomCoordinate(adjacentCoordinates);

            if (!getFromGrid(visited, adjacent)) {
                boolean endReached = dfs(maze, visited, adjacent, end);

                if (endReached) {
                    path.add(current);
                    return true;
                }
            }

            adjacentCoordinates.remove(adjacent);
        }

        return false;
    }

    private List<Coordinate> getAdjacentCoordinates(Maze maze, Coordinate coordinate) {
        List<Coordinate> adjacentCoordinates = new ArrayList<>(DIRECTIONS.size());

        for (Coordinate direction : DIRECTIONS) {
            Coordinate adjacent = coordinate.add(direction);

            if (!isInsideMaze(maze.size(), adjacent) || isWall(maze.grid(), adjacent)) {
                continue;
            }

            adjacentCoordinates.add(adjacent);
        }

        return adjacentCoordinates;
    }
}
