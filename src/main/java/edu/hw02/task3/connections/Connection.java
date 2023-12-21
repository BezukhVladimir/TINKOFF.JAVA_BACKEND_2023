package edu.hw02.task3.connections;

public interface Connection extends AutoCloseable {
    void execute(String command);
}
