package edu.project3.logsparsers.nginx;

import edu.project3.logsparsers.nginx.format.NginxLogItem;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NginxLogsParser {
    private static final String IPV4_ADDRESS_REGEX_PATTERN =
        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final String ESCAPED_DOUBLE_QUOTE = "\\\"";
    private static final Pattern LOG_ITEM_PATTERN = Pattern.compile(
        "(?<remoteAddress>" + IPV4_ADDRESS_REGEX_PATTERN + ")" + " - "
            + "(?<remoteUser>.*)" + " "
            + "\\[" + "(?<timeLocal>.*)" + "]" + " "
            + ESCAPED_DOUBLE_QUOTE + "(?<request>.*)" + ESCAPED_DOUBLE_QUOTE + " "
            + "(?<status>\\d+)" + " "
            + "(?<bodyBytesSent>\\d+)" + " "
            + ESCAPED_DOUBLE_QUOTE + "(?<httpReferer>.*)" + ESCAPED_DOUBLE_QUOTE + " "
            + ESCAPED_DOUBLE_QUOTE + "(?<httpUserAgent>.*)" + ESCAPED_DOUBLE_QUOTE
    );
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH
    );

    private NginxLogsParser() {
    }

    public static List<NginxLogItem> parseLogLines(List<String> lines) {
        return parse(lines);
    }

    public static List<NginxLogItem> parseLogFiles(List<Path> paths) {
        List<String> allLogLines = readAllFiles(paths);
        return parse(allLogLines);
    }

    private static List<String> readAllFiles(List<Path> paths) {
        List<String> allLines = new ArrayList<>();

        for (var path : paths) {
            try {
                allLines.addAll(Files.readAllLines(path));
            } catch (IOException ignored) {
            }
        }

        return allLines;
    }

    private static List<NginxLogItem> parse(List<String> lines) {
        List<NginxLogItem> logItems = new ArrayList<>(lines.size());

        for (var line : lines) {
            Matcher matcher = LOG_ITEM_PATTERN.matcher(line);

            if (matcher.matches()) {
                try {
                    NginxLogItem logItem = NginxLogItem.builder()
                        .setRemoteAddress(InetAddress.getByName(matcher.group("remoteAddress")))
                        .setRemoteUser(matcher.group("remoteUser"))
                        .setTimeLocal(OffsetDateTime.parse(matcher.group("timeLocal"), DATE_TIME_FORMATTER))
                        .setRequest(matcher.group("request"))
                        .setStatus(Integer.parseInt(matcher.group("status")))
                        .setBodyBytesSent(Long.parseLong(matcher.group("bodyBytesSent")))
                        .setHttpReferer(URI.create(matcher.group("httpReferer")))
                        .setHttpUserAgent(matcher.group("httpUserAgent"))
                        .build();

                    logItems.add(logItem);
                } catch (UnknownHostException ignored) {
                }
            }
        }

        return logItems;
    }
}
