package edu.project2;

import edu.project2.mazeengine.generators.DFSGenerator;
import edu.project2.mazeengine.generators.PrimsGenerator;
import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import edu.project2.mazeengine.renderers.DefaultRenderer;
import edu.project2.mazeengine.solvers.DFSSolver;
import edu.project2.mazeengine.solvers.DeadEndSolver;
import edu.project2.mazeengine.solvers.MultiThreadedDFSSolver;

public final class Main {
    private static final int HEIGHT = 5;
    private static final int WIDTH = 5;
    private static final DefaultRenderer DEFAULT_RENDERER = new DefaultRenderer();
    private static final DFSGenerator DFS_GENERATOR = new DFSGenerator();
    private static final PrimsGenerator PRIMS_GENERATOR = new PrimsGenerator();
    private static final DFSSolver DFS_SOLVER = new DFSSolver();
    private static final DeadEndSolver DEAD_END_SOLVER = new DeadEndSolver();
    private static final MultiThreadedDFSSolver MULTI_THREADED_DFS_SOLVER = new MultiThreadedDFSSolver();

    private Main() {
    }

    public static void main(String[] args) {
        Maze dfsMaze   = DFS_GENERATOR.generate(HEIGHT, WIDTH);
        Maze primsMaze = PRIMS_GENERATOR.generate(HEIGHT, WIDTH);

        printMazeResults(dfsMaze);
        printMazeResults(primsMaze);
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    private static void printMazeResults(Maze maze) {
        Coordinate start = new Coordinate(1, 1);
        Coordinate end   = new Coordinate(maze.size().getHeight() - 2, maze.size().getWidth() - 2);

        System.out.println(DEFAULT_RENDERER.render(maze));
        System.out.println(DEFAULT_RENDERER.render(maze, DFS_SOLVER.solve(maze, start, end)));
        System.out.println(DEFAULT_RENDERER.render(maze, DEAD_END_SOLVER.solve(maze, start, end)));
        System.out.println(DEFAULT_RENDERER.render(maze, MULTI_THREADED_DFS_SOLVER.solve(maze, start, end)));
    }
}
