package edu.hw2.task3.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.trace("Successful execution of command: {}", command);
    }

    @Override
    public void close() throws Exception {
        LOGGER.trace("StableConnection closed!");
    }
}
