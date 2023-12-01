package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1337;
    private static final int BUFFER_SIZE = 1024;
    public static final String EXIT_COMMAND = "exit";

    private InputStream inputStream;
    private OutputStream outputStream;

    @Override
    public void run() {
        try {
            try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
                inputStream  = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Scanner scanner = new Scanner(System.in);
                byte[] buffer = new byte[BUFFER_SIZE];
                while (true) {
                    if (scanner.hasNextLine()) {
                        String message = scanner.nextLine();

                        if (EXIT_COMMAND.equalsIgnoreCase(message)) {
                            break;
                        }

                        sendMessage(message);
                        String answer = readAnswer(buffer);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(String message) throws IOException {
        outputStream.write(message.getBytes());
    }

    private String readAnswer(byte[] buffer) throws IOException {
        int bytesRead = inputStream.read(buffer);
        return new String(buffer, 0, bytesRead);
    }
}
