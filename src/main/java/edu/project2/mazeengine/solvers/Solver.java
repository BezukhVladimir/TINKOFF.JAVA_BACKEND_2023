package edu.project2.mazeengine.solvers;

import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
