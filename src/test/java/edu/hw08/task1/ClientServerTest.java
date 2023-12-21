package edu.hw08.task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw06.FilesUtils.clearFile;
import static edu.hw06.FilesUtils.getFirstLineFromFile;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

class ClientServerTest {
    private static final Path LOG = Path.of("src/main/java/edu/hw08/task1/log.txt");

    private Server server;
    private PipedOutputStream serverOut;
    private ByteArrayOutputStream clientOut;
    private InputStream systemIn;
    private PrintStream systemOut;

    @BeforeEach
    void setUp() {
        serverOut = new PipedOutputStream();
        clientOut = new ByteArrayOutputStream();
        systemIn  = System.in;
        systemOut = System.out;

        System.setOut(new PrintStream(clientOut));

        server = new Server();
        server.start();
    }

    @AfterEach
    void shutdown() throws IOException, InterruptedException {
        sleep(500);

        if (server.isAlive()) {
            server.shutdown();
        }

        serverOut.close();
        clientOut.close();
        System.setIn(systemIn);
        System.setOut(systemOut);

        clearFile(LOG);
    }

    @Test
    void testSingleClientServer() throws IOException, InterruptedException {
        // Arrange
        String clientMessage = "семья" + System.lineSeparator() + "exit" + System.lineSeparator();
        String expected = "ip: 127.0.0.1, request: семья, response: Это последний заезд, мамой клянусь.";

        // Act
        try (var inputStream = new ByteArrayInputStream(clientMessage.getBytes())) {
            System.setIn(inputStream);

            Client client = new Client();
            client.start();
            sleep(1000);
        }
        String result = getFirstLineFromFile(LOG);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testMultiClientServer() throws IOException, InterruptedException {
        // Arrange
        String client1Message = "семья" + System.lineSeparator() + "exit" + System.lineSeparator();
        String client2Message = "интеллект" + System.lineSeparator() + "exit" + System.lineSeparator();
        String expected1 = "ip: 127.0.0.1, request: семья, response: Это последний заезд, мамой клянусь.";
        String expected2 = "ip: 127.0.0.1, request: интеллект, response: Чем ниже интеллект, тем громче оскорбления.";

        // Act
        try (var inputStream = new ByteArrayInputStream(client1Message.getBytes())) {
            System.setIn(inputStream);

            Client client = new Client();
            client.start();

            sleep(1000);
        }

        try (var inputStream = new ByteArrayInputStream(client2Message.getBytes())) {
            System.setIn(inputStream);

            Client client = new Client();
            client.start();

            sleep(1000);
        }

        // Assert
        assertThat(containsInFile(expected1)).isTrue();
        assertThat(containsInFile(expected2)).isTrue();
    }

    private boolean containsInFile(String expected) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(LOG)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(expected)) {
                    return true;
                }
            }
        }

        return false;
    }
}
