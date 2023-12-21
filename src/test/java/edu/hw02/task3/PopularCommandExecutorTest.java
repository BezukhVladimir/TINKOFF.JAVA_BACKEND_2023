package edu.hw02.task3;

import edu.hw02.task3.connectionmanagers.DefaultConnectionManager;
import edu.hw02.task3.connectionmanagers.FaultyConnectionManager;
import edu.hw02.task3.connections.FaultyConnection;
import edu.hw02.task3.connections.StableConnection;
import edu.hw02.task3.exceptions.ConnectionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PopularCommandExecutorTest {
    @Test
    @DisplayName("Test default connection manager with stable connection")
    void testDefaultConnectionManagerWithStableConnection() {
        var alwaysSuccessfully = new DefaultConnectionManager(0.0, 0.0);

        assertThat(alwaysSuccessfully.getConnection()).isInstanceOf(StableConnection.class);
    }

    @Test
    @DisplayName("Test default connection manager with faulty connection")
    void testDefaultConnectionManagerWithFaultyConnection() {
        var alwaysFailure = new DefaultConnectionManager(1.0, 0.0);

        assertThat(alwaysFailure.getConnection()).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("Always a faulty connection")
    void testAlwaysFaultyConnection() {
        var connectionManager = new FaultyConnectionManager(1.0);
        int maxAttempts = 10;
        var executor = new PopularCommandExecutor(connectionManager, maxAttempts);

        assertThatThrownBy(executor::updatePackages)
            .isInstanceOf(ConnectionException.class);
    }

    @Test
    @DisplayName("Always a stable connection")
    void testAlwaysStableConnection() {
        var connectionManager = new DefaultConnectionManager(0.0, 0.0);
        int maxAttempts = 10;
        var executor = new PopularCommandExecutor(connectionManager, maxAttempts);

        assertDoesNotThrow(executor::updatePackages);
    }
}
