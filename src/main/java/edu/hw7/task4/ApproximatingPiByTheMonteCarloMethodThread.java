package edu.hw7.task4;

import java.security.SecureRandom;

public final class ApproximatingPiByTheMonteCarloMethodThread extends Thread {
    private final static Square SQUARE = new Square(1.0);
    private final static Circle INSCRIBED_CIRCLE = new Circle(
        new Point2D(0.5, 0.5), SQUARE.side() / 2.0
    );

    private final long totalPointsNumber;
    private long pointsInCircleCount;

    public ApproximatingPiByTheMonteCarloMethodThread(long totalPointsNumber) {
        this.totalPointsNumber = totalPointsNumber;
        this.pointsInCircleCount = 0;
    }

    public long getPointsInCircleCount() {
        return pointsInCircleCount;
    }

    @Override
    public void run() {
        var random = new SecureRandom();
        Point2D randomPoint2D;

        for (int i = 0; i < totalPointsNumber; i++) {
            randomPoint2D = new Point2D(
                random.nextDouble(),
                random.nextDouble()
            );

            if (isInInscribedCircle(randomPoint2D)) {
                pointsInCircleCount++;
            }
        }
    }

    private boolean isInInscribedCircle(Point2D point2D) {
        return Math.pow(INSCRIBED_CIRCLE.center().x() - point2D.x(), 2.0)
             + Math.pow(INSCRIBED_CIRCLE.center().y() - point2D.y(), 2.0)
            <= Math.pow(INSCRIBED_CIRCLE.radius(), 2.0);
    }
}
