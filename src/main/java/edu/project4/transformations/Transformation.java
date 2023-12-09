package edu.project4.transformations;

import edu.project4.models.Point;
import java.util.function.Function;

@FunctionalInterface
public interface Transformation extends Function<Point, Point> {
}
