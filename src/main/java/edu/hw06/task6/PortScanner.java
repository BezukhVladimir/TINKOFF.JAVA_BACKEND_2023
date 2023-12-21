package edu.hw06.task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;

@SuppressWarnings({"RegexpSinglelineJava"})
public final class PortScanner {
    private PortScanner() {
    }

    private static final String FORMAT = "%-9s %-5d %-16s %-35s" + System.lineSeparator();
    private static final String HEADER = "Protocol  Port  Status  Service";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;
    private static final Map<Integer, String> PORTS = Map.of(
        135, "EPMAP",
        137, "Служба имен NetBIOS",
        139, "Служба сеансов NetBIOS",
        445, "Microsoft-DS Active Directory",
        843, "Adobe Flash",
        1900, "SSDP",
        3702, "Динамическое обнаружение веб-служб",
        5353, "Многоадресный DNS",
        17500, "Dropbox",
        27017, "MongoDB"
    );

    private enum Protocol {
        TCP, UDP
    }

    private enum Status {
        OPEN, CLOSED
    }

    private enum Color {
        RESET, RED, GREEN, BLUE
    }

    public static void scanPorts() {
        System.out.println(setColor(HEADER, Color.BLUE));
        for (int port = MIN_PORT; port <= MAX_PORT; ++port) {
            scanTcpPort(port);
            scanUdpPort(port);
        }
    }

    private static void scanTcpPort(int port) {
        Status status = isTcpPortClosed(port);
        scanPort(Protocol.TCP, port, status);
    }

    private static void scanUdpPort(int port) {
        Status status = isUdpPortClosed(port);
        scanPort(Protocol.UDP, port, status);
    }

    private static Status isTcpPortClosed(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return Status.OPEN;
        } catch (Exception e) {
            return Status.CLOSED;
        }
    }

    private static Status isUdpPortClosed(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return Status.OPEN;
        } catch (Exception e) {
            return Status.CLOSED;
        }
    }

    private static void scanPort(Protocol protocol, int port, Status status) {
        String service = PORTS.getOrDefault(port, "");

        if (!service.isEmpty()) {
            System.out.printf(
                FORMAT,
                protocol.toString(),
                port,
                setColor(status.toString(), getStatusColor(status)),
                service
            );
        }
    }

    private static String setColor(String string, Color color) {
        return switch (color) {
            case RESET -> ANSI_RESET + string;
            case RED   -> ANSI_RED   + string + ANSI_RESET;
            case GREEN -> ANSI_GREEN + string + ANSI_RESET;
            case BLUE  -> ANSI_BLUE  + string + ANSI_RESET;
        };
    }

    private static Color getStatusColor(Status status) {
        return switch (status) {
            case OPEN   -> Color.GREEN;
            case CLOSED -> Color.RED;
        };
    }
}
