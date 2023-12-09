package edu.project4.transformations;

import edu.project4.models.Point;

public class Spherical implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r2 = x * x + y * y;

        return new Point(x / r2, y / r2);
    }
}
