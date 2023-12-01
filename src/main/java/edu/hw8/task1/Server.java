package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {
    private static final int PORT = 1337;
    private static final int MAX_CONNECTIONS = 2;

    private final ExecutorService executorService;

    public Server() {
        this.executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    }

    @Override
    public void run() {
        try (var serverSocket = new ServerSocket(PORT)) {
            int clientCount = 0;

            while (clientCount < MAX_CONNECTIONS) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                executorService.execute(clientHandler);
                ++clientCount;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
