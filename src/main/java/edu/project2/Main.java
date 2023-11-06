package edu.project2;

import edu.project2.mazeengine.generators.DFSGenerator;
import edu.project2.mazeengine.generators.PrimsGenerator;
import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import edu.project2.mazeengine.renderers.DefaultRenderer;
import edu.project2.mazeengine.solvers.DFSSolver;
import edu.project2.mazeengine.solvers.DeadEndSolver;

public final class Main {
    private final static int HEIGHT = 5;
    private final static int WIDTH = 5;
    private final static DefaultRenderer DEFAULT_RENDERER = new DefaultRenderer();
    private final static DFSGenerator DFS_GENERATOR = new DFSGenerator();
    private final static PrimsGenerator PRIMS_GENERATOR = new PrimsGenerator();
    private final static DFSSolver DFS_SOLVER = new DFSSolver();
    private final static DeadEndSolver DEAD_END_SOLVER = new DeadEndSolver();

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
    }
}
