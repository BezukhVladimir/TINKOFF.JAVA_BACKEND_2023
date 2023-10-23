package edu.hw2.task3.connectionmanagers;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.StableConnection;

public class DefaultConnectionManager implements ConnectionManager {
    private final double faultyConnectionChance;
    private final double faultyConnectionFailureChance;

    /**
     * Creates a DefaultConnectionManager with specified chances for returning faulty connections.
     *
     * @param faultyConnectionChance        The chance of returning a faulty connection.
     *                                      Must be between 0.0 and 1.0 (inclusive).
     * @param faultyConnectionFailureChance The chance of failure in a faulty connection.
     *                                      Must be between 0.0 and 1.0 (inclusive).
     * @throws IllegalArgumentException If either of the chances is not within the valid range.
     */
    public DefaultConnectionManager(double faultyConnectionChance, double faultyConnectionFailureChance) {
        if (faultyConnectionChance < 0.0 || faultyConnectionChance > 1.0) {
            throw new IllegalArgumentException();
        }

        if (faultyConnectionFailureChance < 0.0 || faultyConnectionFailureChance > 1.0) {
            throw new IllegalArgumentException();
        }

        this.faultyConnectionChance = faultyConnectionChance;
        this.faultyConnectionFailureChance = faultyConnectionFailureChance;
    }

    @Override
    public Connection getConnection() {
        if (Math.random() < faultyConnectionChance) {
            return new FaultyConnection(faultyConnectionFailureChance);
        }

        return new StableConnection();
    }
}
