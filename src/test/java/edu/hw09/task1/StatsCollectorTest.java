package edu.hw09.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

final class StatsCollectorTest {
    private StatsCollectorTest() {
    }

    private static final int THREADS_NUMBER = 2;
    private static final double DELTA = 1e-10;
    private StatsCollector collector;

    @BeforeEach
    void createStatsCollector() {
        collector = new StatsCollector(THREADS_NUMBER);
    }

    @Test
    void oneMetric() throws InterruptedException {
        // Arrange
        String metricName = "name";
        double[] values = new double[] { 0.1, 0.05, 1.4, 5.1, 0.3 };
        Metric expected = new Metric(
            "name", 6.95, 1.39, 5.1, 0.05
        );

        // Act
        collector.push(metricName, values);
        sleep(10);

        // Assert
        List<Metric> result = collector.stats();

        assertThat(result).hasSize(1);
        for (var metric : result) {
            assertThat(metric.name()).isEqualTo(expected.name());
            assertThat(metric.sum()).isBetween(expected.sum() - DELTA, expected.sum() + DELTA);
            assertThat(metric.average()).isBetween(expected.average() - DELTA, expected.average() + DELTA);
            assertThat(metric.max()).isEqualTo(expected.max());
            assertThat(metric.min()).isEqualTo(expected.min());
        }
    }

    @Test
    void severalMetrics() throws InterruptedException {
        // Arrange
        Map<String, double[]> stats = Map.of(
            "name1", new double[] { 0.0, 1.0, 2.0, 3.0, 4.0 },
            "name2", new double[] { 5.0, 6.0, 7.0, 8.0, 9.0 },
            "name3", new double[] { 0.123456789 },
            "name4", new double[] { 0.0 }
        );

        List<Metric> expects = List.of(
            new Metric("name1", 10.0, 2.0, 4.0, 0.0),
            new Metric("name2", 35.0, 7.0, 9.0, 5.0),
            new Metric("name3", 0.123456789, 0.123456789, 0.123456789, 0.123456789),
            new Metric("name4", 0.0, 0.0, 0.0, 0.0)
        );

        // Act
        Thread[] providers = new Thread[stats.size()];
        var latch = new CountDownLatch(stats.size());
        int i = 0;
        for (var entry : stats.entrySet()) {
            providers[i] = new Thread(() -> {
                latch.countDown();

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                collector.push(entry.getKey(), entry.getValue());

                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            providers[i].start();
            ++i;
        }

        for (var provider : providers) {
            provider.join();
        }

        // Assert
        List<Metric> result = collector.stats();

        assertThat(result).hasSize(stats.size());
        for (var metric : result) {
            var expected = expects.stream()
                .filter(e -> e.name().equals(metric.name()))
                .findFirst()
                .orElse(null);

            assertThat(metric.name()).isEqualTo(expected.name());
            assertThat(metric.sum()).isBetween(expected.sum() - DELTA, expected.sum() + DELTA);
            assertThat(metric.average()).isBetween(expected.average() - DELTA, expected.average() + DELTA);
            assertThat(metric.max()).isEqualTo(expected.max());
            assertThat(metric.min()).isEqualTo(expected.min());
        }
    }
}
