package edu.hw7.task4;

import java.security.SecureRandom;

public final class ApproximatingPiByTheMonteCarloMethod {
    private ApproximatingPiByTheMonteCarloMethod() {
    }

    private final static Square SQUARE = new Square(1.0);
    private final static Circle INSCRIBED_CIRCLE = new Circle(
        new Point2D(0.5, 0.5), SQUARE.side() / 2.0
    );
    private final static double CONVERSION_MULTIPLIER = 4.0;

    public static double singleThreadApproximate(long totalPointsNumber) {
        var random = new SecureRandom();
        Point2D randomPoint2D;

        long pointsInCircleCount = 0;
        for (int i = 0; i < totalPointsNumber; i++) {
            randomPoint2D = new Point2D(
                random.nextDouble(),
                random.nextDouble()
            );

            if (isInInscribedCircle(randomPoint2D)) {
                pointsInCircleCount++;
            }
        }

        return calculate(totalPointsNumber, pointsInCircleCount);
    }

    public static double multiThreadApproximate(int threadsNumber, long totalPointsNumber) {
        long pointsInCircleCount = 0;
        long pointsNumberInThread = totalPointsNumber / threadsNumber;

        var threads = new ApproximatingPiByTheMonteCarloMethodThread[threadsNumber];

        for (int i = 0; i < threadsNumber; ++i) {
            threads[i] = new ApproximatingPiByTheMonteCarloMethodThread(pointsNumberInThread);
            threads[i].start();
        }

        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }

            pointsInCircleCount += thread.getPointsInCircleCount();
        }

        return calculate(totalPointsNumber, pointsInCircleCount);
    }

    private static boolean isInInscribedCircle(Point2D point2D) {
        return Math.pow(INSCRIBED_CIRCLE.center().x() - point2D.x(), 2.0)
             + Math.pow(INSCRIBED_CIRCLE.center().y() - point2D.y(), 2.0)
            <= Math.pow(INSCRIBED_CIRCLE.radius(), 2.0);
    }

    private static double calculate(long totalPointsNumber, long pointsInCircleCount) {
        return CONVERSION_MULTIPLIER * ((double) pointsInCircleCount / totalPointsNumber);
    }
}
