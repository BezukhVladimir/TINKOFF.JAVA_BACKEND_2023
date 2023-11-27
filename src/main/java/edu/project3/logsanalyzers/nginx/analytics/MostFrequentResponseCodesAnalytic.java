package edu.project3.logsanalyzers.nginx.analytics;

import edu.project3.logsparsers.nginx.format.NginxLogItem;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static edu.project3.logsreporter.LogsReporter.create1x3row;

public final class MostFrequentResponseCodesAnalytic {
    private MostFrequentResponseCodesAnalytic() {
    }

    private static final int TOP_5 = 5;

    /**
     * Analyzes the list of NginxLogItems objects and returns
     * a String representing a 5x3 table. The first col is the
     * status code, the second col is the name of the status code,
     * and the third col is the count.
     *
     * @param nginxLogItems List of NginxLogItem objects to analyze.
     * @return A String representing a 5x3 table.
    */
    public static String analyze(List<NginxLogItem> nginxLogItems) {
        Map<String, String> top5codes = nginxLogItems.stream()
            .map(item -> Integer.toString(item.status()))
            .collect(
                Collectors.groupingBy(
                    Function.identity(), Collectors.counting()
                )
            )
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(TOP_5)
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry.getValue().toString(),
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                )
            );

        var builder = new StringBuilder();
        top5codes.forEach((status, count) ->
            builder.append(
                create1x3row(status, getStatusName(status), count)
            )
        );

        return builder.toString();
    }

    private static String getStatusName(String statusCode) {
        return switch (statusCode) {
            case "200" -> "OK";
            case "201" -> "Created";
            case "204" -> "No Content";
            case "400" -> "Bad Request";
            case "401" -> "Unauthorized";
            case "403" -> "Forbidden";
            case "404" -> "Not Found";
            case "500" -> "Internal Server Error";
            default -> "Unknown";
        };
    }
}
