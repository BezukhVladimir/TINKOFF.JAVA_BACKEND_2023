package edu.hw2.task3;

import edu.hw2.task3.connectionmanagers.ConnectionManager;
import edu.hw2.task3.exceptions.ConnectionException;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        for (int attempt = 1; attempt <= maxAttempts; ++attempt) {
            try (var connection = manager.getConnection()) {
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                if (attempt == maxAttempts) {
                    throw new ConnectionException(
                        "Failed to execute command (" + command + ")"
                            + "after " + maxAttempts + " attempts", e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
