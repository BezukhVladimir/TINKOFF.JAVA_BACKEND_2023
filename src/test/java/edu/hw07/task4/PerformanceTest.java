package edu.hw07.task4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Disabled
class PerformanceTest {
    private record Result(
       double averageExecutionTimeInSeconds,
       double averageDelta
    ) {
    }

    private static final int START_THREADS_NUMBER = 2;
    private static final int FINISH_THREADS_NUMBER = 6;
    private static final int THREADS_NUMBER_SHIFT = 2;
    private static final long TOTAL_SIMULATIONS_NUMBER = 10L;

    @ParameterizedTest
    @ValueSource (longs = {
        10_000L,
        100_000L,
        1_000_000L,
        10_000_000L
    })
    void performanceTests(long totalPointsNumber) {
        Result singleThread = singleThreadVersion(totalPointsNumber);

        for (int threadsNumber = START_THREADS_NUMBER;
             threadsNumber <= FINISH_THREADS_NUMBER;
             threadsNumber += THREADS_NUMBER_SHIFT
        ) {
            Result multiThread = multiThreadVersion(threadsNumber, totalPointsNumber);
            printResultComparison(singleThread, multiThread, threadsNumber, totalPointsNumber);
        }
    }

    private Result singleThreadVersion(long totalPointsNumber) {
        double totalExecutionTimeInSeconds = 0.0;
        double totalDelta = 0.0;

        for (int i = 0; i < TOTAL_SIMULATIONS_NUMBER; ++i) {
            var startTime = System.nanoTime();
            double result = ApproximatingPiByTheMonteCarloMethod
                .singleThreadApproximate(totalPointsNumber);
            var endTime = System.nanoTime();

            double delta = Math.abs(Math.PI - result);

            totalExecutionTimeInSeconds += (double) (endTime - startTime) / 1_000_000_000.0;
            totalDelta += delta;
        }

        double averageExecutingTimeInSeconds = totalExecutionTimeInSeconds / TOTAL_SIMULATIONS_NUMBER;
        double averageDelta = totalDelta / TOTAL_SIMULATIONS_NUMBER;

        return new Result(averageExecutingTimeInSeconds, averageDelta);
    }

    private Result multiThreadVersion(int threadsNumber, long totalPointsNumber) {
        double totalExecutionTimeInSeconds = 0.0;
        double totalDelta = 0.0;

        for (int i = 0; i < TOTAL_SIMULATIONS_NUMBER; ++i) {
            var startTime = System.nanoTime();
            double result = ApproximatingPiByTheMonteCarloMethod
                .multiThreadApproximate(threadsNumber, totalPointsNumber);
            var endTime = System.nanoTime();

            double delta = Math.abs(Math.PI - result);

            totalExecutionTimeInSeconds += (double) (endTime - startTime) / 1_000_000_000.0;
            totalDelta += delta;
        }

        double averageExecutingTimeInSeconds = totalExecutionTimeInSeconds / TOTAL_SIMULATIONS_NUMBER;
        double averageDelta = totalDelta / TOTAL_SIMULATIONS_NUMBER;

        return new Result(averageExecutingTimeInSeconds, averageDelta);
    }

    private void printResultComparison(
        Result singleThread,
        Result multiThread,
        int threadsNumber,
        long totalPointsNumber
    ) {
        System.out.println(
            "Comparison results for "
            + totalPointsNumber + " points and "
            + threadsNumber + " threads ("
            + TOTAL_SIMULATIONS_NUMBER + " simulations):"
        );
        System.out.println(
            "Single thread: average executing time in seconds — "
            + singleThread.averageExecutionTimeInSeconds()
        );
        System.out.println(
            "Multi thread: average executing time in seconds — "
            + multiThread.averageExecutionTimeInSeconds()
        );

        double averageExecutionTimeDifference = Math.abs(
            multiThread.averageExecutionTimeInSeconds()
                - singleThread.averageExecutionTimeInSeconds()
        );
        double ratio =
            singleThread.averageExecutionTimeInSeconds()
                / multiThread.averageExecutionTimeInSeconds();

        System.out.printf("Difference: %.5f seconds%n", averageExecutionTimeDifference);
        System.out.printf("Multi thread is %.2fx faster than single thread%n", ratio);

        System.out.printf("Single thread: average delta — %.5f%n", singleThread.averageDelta());
        System.out.printf("Multi thread: average delta — %.5f%n", multiThread.averageDelta());
        System.out.println();
    }
}
