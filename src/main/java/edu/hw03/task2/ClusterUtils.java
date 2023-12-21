package edu.hw03.task2;

import java.util.ArrayList;
import java.util.List;

public final class ClusterUtils {
    private ClusterUtils() {
    }

    public static List<String> clusterize(String input) {
        int balance = 0;
        List<String> clusters = new ArrayList<>();
        StringBuilder cluster = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == '(') {
                ++balance;
            }

            if (c == ')') {
                --balance;
            }

            cluster.append(c);

            if (balance == 0) {
                clusters.add(cluster.toString());
                cluster.delete(0, cluster.length());
            }
        }

        return clusters;
    }
}
