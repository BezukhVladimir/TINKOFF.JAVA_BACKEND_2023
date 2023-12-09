package edu.project4.transformations;

import edu.project4.models.Point;

public class Swirl implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r2 = x * x + y * y;
        double sinR2 = Math.sin(r2);
        double cosR2 = Math.cos(r2);

        return new Point(
            x * sinR2 - y * cosR2,
            x * cosR2 + y * sinR2
        );
    }
}
