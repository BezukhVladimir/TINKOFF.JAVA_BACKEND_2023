package edu.hw01.task0;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HelloWorld {
    private HelloWorld() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Outputs the phrase "Привет, мир!" to the console using the LOGGER.info() method.
     */
    public static void logHelloWorld() {
        LOGGER.info("Привет, мир!");
    }
}
