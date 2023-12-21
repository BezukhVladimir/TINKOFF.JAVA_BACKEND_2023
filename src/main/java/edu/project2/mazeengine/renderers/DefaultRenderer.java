package edu.project2.mazeengine.renderers;

import edu.project2.mazeengine.models.Cell;
import edu.project2.mazeengine.models.Coordinate;
import edu.project2.mazeengine.models.Maze;
import edu.project2.mazeengine.settings.SettingsManager;
import java.util.List;
import static edu.project2.mazeengine.utils.Utils.isPassage;
import static edu.project2.mazeengine.utils.Utils.isWall;

public class DefaultRenderer implements Renderer {
    private final static char WALL_SYMBOL;
    private final static char PASSAGE_SYMBOL;
    private final static char PATH_SYMBOL;
    private final static char INCORRECT_PATH_SYMBOL;

    @Override
    public String render(Maze maze) {
        var mazeRender = new StringBuilder();
        Cell[][] grid = maze.grid();

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (isWall(cell)) {
                    mazeRender.append(WALL_SYMBOL);
                } else {
                    mazeRender.append(PASSAGE_SYMBOL);
                }
            }

            mazeRender.append(System.lineSeparator());
        }

        return mazeRender.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        var mazeRender = new StringBuilder();
        Cell[][] grid = maze.grid();

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (isCellBelongsToPath(cell, path)) {
                    mazeRender.append(getPathSymbol(cell));
                } else if (isWall(cell)) {
                    mazeRender.append(WALL_SYMBOL);
                } else {
                    mazeRender.append(PASSAGE_SYMBOL);
                }
            }

            mazeRender.append(System.lineSeparator());
        }

        return mazeRender.toString();
    }

    private boolean isCellBelongsToPath(Cell cell, List<Coordinate> path) {
        return path.contains(cell.getCoordinate());
    }

    private char getPathSymbol(Cell cell) {
        return isPassage(cell) ? PATH_SYMBOL : INCORRECT_PATH_SYMBOL;
    }

    static {
        var settings = SettingsManager.getInstance();

        WALL_SYMBOL           = settings.getChar("DEFAULT_WALL_SYMBOL");
        PASSAGE_SYMBOL        = settings.getChar("DEFAULT_PASSAGE_SYMBOL");
        PATH_SYMBOL           = settings.getChar("DEFAULT_PATH_SYMBOL");
        INCORRECT_PATH_SYMBOL = settings.getChar("DEFAULT_INCORRECT_PATH_SYMBOL");
    }
}
