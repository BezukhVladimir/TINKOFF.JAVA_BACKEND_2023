package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class ClientHandler implements Runnable {
    private static final int BUFFER_SIZE = 1024;
    private static final Path LOG = Path.of("src/main/java/edu/hw8/task1/log.txt");
    private static final Map<String, String> EXCUSES = Map.of(
        "личности", "Не переходи на личности там, где их нет.",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами.",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно. Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления.",
        "семья", "Это последний заезд, мамой клянусь."
    );
    public static final String DEFAULT_EXCUSE
        = "Напишите, пожалуйста, позже. Я в заложниках у тёплого одеяла и не могу вам ответить.";

    private final Socket clientSocket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public ClientHandler(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.inputStream  = clientSocket.getInputStream();
            this.outputStream = clientSocket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                int bytesRead = inputStream.read(buffer);

                if (bytesRead != -1) {
                    String request = new String(buffer, 0, bytesRead);

                    String response = getExcuseForRequest(request);
                    addToLog(
                        clientSocket.getInetAddress().getHostAddress(),
                        request,
                        response
                    );
                    outputStream.write(response.getBytes());
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getExcuseForRequest(String request) {
        return EXCUSES.getOrDefault(request.toLowerCase(), DEFAULT_EXCUSE);
    }

    private void addToLog(String ipAddress, String request, String response) throws IOException {
        String logEntry = String.format(
            "ip: %s, request: %s, response: %s%n",
            ipAddress, request, response
        );
        Files.write(LOG, logEntry.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }
}
