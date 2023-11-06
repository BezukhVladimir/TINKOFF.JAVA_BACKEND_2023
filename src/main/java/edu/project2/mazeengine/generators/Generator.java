package edu.project2.mazeengine.generators;

import edu.project2.mazeengine.models.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
