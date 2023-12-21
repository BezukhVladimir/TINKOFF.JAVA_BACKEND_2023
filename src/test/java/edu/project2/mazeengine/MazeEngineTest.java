package edu.project2.mazeengine;

import edu.project2.mazeengine.generators.DFSGenerator;
import edu.project2.mazeengine.generators.Generator;
import edu.project2.mazeengine.generators.PrimsGenerator;
import edu.project2.mazeengine.models.Cell;
import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import edu.project2.mazeengine.renderers.DefaultRenderer;
import edu.project2.mazeengine.renderers.Renderer;
import edu.project2.mazeengine.settings.SettingsManager;
import edu.project2.mazeengine.solvers.DFSSolver;
import edu.project2.mazeengine.solvers.DeadEndSolver;
import edu.project2.mazeengine.solvers.MultiThreadedDFSSolver;
import edu.project2.mazeengine.solvers.Solver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class MazeEngineTest {
    private static final char WALL_SYMBOL;
    private static final char PASSAGE_SYMBOL;
    private static final char PATH_SYMBOL;
    private static final char INCORRECT_PATH_SYMBOL;
    private static final int HEIGHT = 5;
    private static final int WIDTH = 5;
    private static final Renderer DEFAULT_RENDERER = new DefaultRenderer();
    private static final Solver DFS_SOLVER = new DFSSolver();
    private static final Solver DEAD_END_SOLVER = new DeadEndSolver();
    private static final MultiThreadedDFSSolver MULTI_THREADED_DFS_SOLVER = new MultiThreadedDFSSolver();


    @Test
    void testDefaultRenderWall() {
        // Arrange
        Maze maze = get1x1Maze(Cell.Type.WALL);
        String expected = WALL_SYMBOL + System.lineSeparator();

        // Act
        String result = DEFAULT_RENDERER.render(maze);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDefaultRenderPassage() {
        // Arrange
        Maze maze = get1x1Maze(Cell.Type.PASSAGE);
        String expected = PASSAGE_SYMBOL + System.lineSeparator();

        // Act
        String result = DEFAULT_RENDERER.render(maze);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void testDefaultRenderPath() {
        // Arrange
        Maze maze = get1x1Maze(Cell.Type.PASSAGE);
        List<Coordinate> path = new ArrayList<>(List.of(new Coordinate(0, 0)));
        String expected = PATH_SYMBOL + System.lineSeparator();

        // Act
        String result = DEFAULT_RENDERER.render(maze, path);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDefaultRenderIncorrectPath() {
        // Arrange
        Maze maze = get1x1Maze(Cell.Type.WALL);
        List<Coordinate> path = new ArrayList<>(List.of(new Coordinate(0, 0)));
        String expected = INCORRECT_PATH_SYMBOL + System.lineSeparator();

        // Act
        String result = DEFAULT_RENDERER.render(maze, path);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void testGenerators(Generator generator) {
        // Assert
        assertThatCode(() -> generator.generate(HEIGHT, WIDTH)).doesNotThrowAnyException();
    }

    @Test
    void testDFSSolver() {
        // Arrange
        Maze maze = get5x5Maze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end   = new Coordinate(3, 1);
        List<Coordinate> expected = new ArrayList<>(List.of(
                end,
                new Coordinate(3, 2),
                new Coordinate(2, 2),
                new Coordinate(1, 2),
                start
            ));

        // Act
        List<Coordinate> result = DFS_SOLVER.solve(maze, start, end);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDeadEndSolver() {
        // Arrange
        Maze maze = get5x5Maze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end   = new Coordinate(3, 1);
        List<Coordinate> expected = new ArrayList<>(List.of(
            start,
            new Coordinate(1, 2),
            new Coordinate(2, 2),
            new Coordinate(3, 2),
            end
        ));

        // Act
        List<Coordinate> result = DEAD_END_SOLVER.solve(maze, start, end);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    private static Maze get1x1Maze(Cell.Type type) {
        return new Maze(
            new Maze.Size(1, 1),
            new Cell[][]{{
                getCell(0, 0, type)
            }});
    }

    @Test
    void testMultiThreadedDFSSolver() {
        // Arrange
        Maze maze = get5x5Maze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end   = new Coordinate(3, 1);
        List<Coordinate> expected = new ArrayList<>(List.of(
            end,
            new Coordinate(3, 2),
            new Coordinate(2, 2),
            new Coordinate(1, 2),
            start
        ));

        // Act
        List<Coordinate> result = MULTI_THREADED_DFS_SOLVER.solve(maze, start, end);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    private static Maze get5x5Maze() {
        return new Maze(
            new Maze.Size(5, 5),
            new Cell[][]{
                {
                    getCell(0, 0, Cell.Type.WALL),
                    getCell(0, 1, Cell.Type.WALL),
                    getCell(0, 2, Cell.Type.WALL),
                    getCell(0, 3, Cell.Type.WALL),
                    getCell(0, 4, Cell.Type.WALL)
                },
                {
                    getCell(1, 0, Cell.Type.WALL),
                    getCell(1, 1, Cell.Type.PASSAGE),
                    getCell(1, 2, Cell.Type.PASSAGE),
                    getCell(1, 3, Cell.Type.PASSAGE),
                    getCell(1, 4, Cell.Type.WALL)
                },
                {
                    getCell(2, 0, Cell.Type.WALL),
                    getCell(2, 1, Cell.Type.WALL),
                    getCell(2, 2, Cell.Type.PASSAGE),
                    getCell(2, 3, Cell.Type.WALL),
                    getCell(2, 4, Cell.Type.WALL)
                },
                {
                    getCell(3, 0, Cell.Type.WALL),
                    getCell(3, 1, Cell.Type.PASSAGE),
                    getCell(3, 2, Cell.Type.PASSAGE),
                    getCell(3, 3, Cell.Type.PASSAGE),
                    getCell(3, 4, Cell.Type.WALL)
                },
                {
                    getCell(4, 0, Cell.Type.WALL),
                    getCell(4, 1, Cell.Type.WALL),
                    getCell(4, 2, Cell.Type.WALL),
                    getCell(4, 3, Cell.Type.WALL),
                    getCell(4, 4, Cell.Type.WALL)
                }
            });
    }

    private static Cell getCell(int row, int col, Cell.Type type) {
        return new Cell(new Coordinate(row, col), type);
    }

    private static Arguments[] generators() {
        return new Arguments[] {
            Arguments.of(new PrimsGenerator()),
            Arguments.of(new DFSGenerator())
        };
    }

    static {
        var settings = SettingsManager.getInstance();

        WALL_SYMBOL           = settings.getChar("DEFAULT_WALL_SYMBOL");
        PASSAGE_SYMBOL        = settings.getChar("DEFAULT_PASSAGE_SYMBOL");
        PATH_SYMBOL           = settings.getChar("DEFAULT_PATH_SYMBOL");
        INCORRECT_PATH_SYMBOL = settings.getChar("DEFAULT_INCORRECT_PATH_SYMBOL");
    }
}
