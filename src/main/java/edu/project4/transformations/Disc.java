package edu.project4.transformations;

import edu.project4.models.Point;

public class Disc implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double argument = Math.PI * Math.sqrt(x * x + y * y);
        double theta = Math.atan(x / y);
        double coefficient = theta / Math.PI;

        return new Point(
            coefficient * Math.sin(argument),
            coefficient * Math.cos(argument)
        );
    }
}
