package edu.hw02.task3.connectionmanagers;

import edu.hw02.task3.connections.Connection;
import edu.hw02.task3.connections.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager {
    private final double failureChance;

    /**
     * Creates a FaultyConnectionManager with a specified failure chance.
     *
     * @param failureChance The chance of a connection failing. Must be between 0.0 and 1.0 (inclusive).
     * @throws IllegalArgumentException If the chance is not within the valid range.
     */
    public FaultyConnectionManager(double failureChance) {
        if (failureChance < 0.0 || failureChance > 1.0) {
            throw new IllegalArgumentException();
        }

        this.failureChance = failureChance;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(failureChance);
    }
}
