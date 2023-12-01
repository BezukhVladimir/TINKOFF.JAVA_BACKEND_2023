package edu.project2.mazeengine.solvers;

import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import static edu.project2.mazeengine.utils.Utils.checkPath;
import static edu.project2.mazeengine.utils.Utils.createBooleanGrid;
import static edu.project2.mazeengine.utils.Utils.getFromGrid;
import static edu.project2.mazeengine.utils.Utils.isInsideMaze;
import static edu.project2.mazeengine.utils.Utils.isWall;
import static edu.project2.mazeengine.utils.Utils.setInGrid;

public class MultiThreadedDFSSolver implements Solver {
    private final static List<Coordinate> DIRECTIONS = List.of(
        new Coordinate(0, -1),
        new Coordinate(-1, 0),
        new Coordinate(0, 1),
        new Coordinate(1, 0)
    );
    private List<Coordinate> path;
    private volatile Boolean[][] visited;

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
        visited = createBooleanGrid(maze.size());

        try (var forkJoinPool = new ForkJoinPool()) {
            var dfsTask = new DFSTask(maze, start, end);
            path.addAll(forkJoinPool.invoke(dfsTask));
        }
    }

    private List<Coordinate> getAdjacentCoordinates(Maze maze, Coordinate coordinate) {
        return DIRECTIONS.stream()
            .map(coordinate::add)
            .filter(adjacent -> isInsideMaze(maze.size(), adjacent) && !isWall(maze.grid(), adjacent))
            .collect(Collectors.toList());
    }

    private class DFSTask extends RecursiveTask<List<Coordinate>> {
        private final Maze maze;
        private final Coordinate current;
        private final Coordinate end;

        private DFSTask(Maze maze, Coordinate current, Coordinate end) {
            this.maze    = maze;
            this.current = current;
            this.end     = end;
        }

        @Override
        protected List<Coordinate> compute() {
            List<Coordinate> localPath = new ArrayList<>();

            if (current.equals(end)) {
                localPath.add(current);
                return localPath;
            }

            setInGrid(visited, current, true);

            List<Coordinate> adjacentCoordinates = getAdjacentCoordinates(maze, current);
            List<DFSTask> subtasks = new ArrayList<>();
            for (var adjacent : adjacentCoordinates) {
                if (!getFromGrid(visited, adjacent)) {
                    DFSTask subtask = new DFSTask(maze, adjacent, end);
                    subtask.fork();
                    subtasks.add(subtask);
                }
            }

            for (var subtask : subtasks) {
                List<Coordinate> result = subtask.join();

                if (result.contains(end)) {
                    localPath.addAll(result);
                    localPath.add(current);
                    break;
                }
            }

            return localPath;
        }
    }
}
