package edu.project4.transformations;

import edu.project4.models.Point;

public class Linear implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(point.x(), point.y());
    }
}
