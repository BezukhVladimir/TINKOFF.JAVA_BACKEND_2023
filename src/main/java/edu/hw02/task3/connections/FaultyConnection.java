package edu.hw02.task3.connections;

import edu.hw02.task3.exceptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();

    private final double failureChance;

    /**
     * Creates a FaultyConnection with the specified failure chance.
     *
     * @param failureChance The chance of connection failure. Must be between 0.0 and 1.0 (inclusive).
     * @throws IllegalArgumentException If the failureChance is not within the valid range.
     */
    public FaultyConnection(double failureChance) {
        if (failureChance < 0.0 || failureChance > 1.0) {
            throw new IllegalArgumentException();
        }

        this.failureChance = failureChance;
    }

    @Override
    public void execute(String command) {
        if (Math.random() < failureChance) {
            throw new ConnectionException("Failed to execute command");
        }

        LOGGER.trace("Successful execution of command: {}", command);
    }

    @Override
    public void close() throws Exception {
        LOGGER.trace("FaultyConnection closed!");
    }
}
