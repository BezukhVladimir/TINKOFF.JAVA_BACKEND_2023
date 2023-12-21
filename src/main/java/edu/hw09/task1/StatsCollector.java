package edu.hw09.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {
    private final ExecutorService executorService;
    private final Queue<Metric> metrics = new ConcurrentLinkedQueue<>();

    public StatsCollector(int nThreads) {
        executorService = Executors.newFixedThreadPool(nThreads);
    }

    public void push(String metricName, double[] values) {
        executorService.execute(
            putMetric(metricName, values)
        );
    }

    public List<Metric> stats() {
        return new ArrayList<>(metrics);
    }

    private Runnable putMetric(String name, double[] values) {
        return () -> {
            double sum = 0.0;
            double max = Double.NEGATIVE_INFINITY;
            double min = Double.POSITIVE_INFINITY;

            for (double value : values) {
                if (value > max) {
                    max = value;
                }

                if (value < min) {
                    min = value;
                }

                sum += value;
            }

            metrics.add(new Metric(
                name, sum, sum / values.length, max, min
            ));
        };
    }
}
