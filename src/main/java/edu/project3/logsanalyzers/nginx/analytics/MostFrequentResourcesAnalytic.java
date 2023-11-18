package edu.project3.logsanalyzers.nginx.analytics;

import edu.project3.logsparsers.nginx.format.NginxLogItem;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static edu.project3.logsreporter.LogsReporter.create1x2row;

public final class MostFrequentResourcesAnalytic {
    private MostFrequentResourcesAnalytic() {
    }

    private static final int NUMBER_OF_TOKENS = 3;
    private static final int TOP_5 = 5;

    /**
     * Analyzes the list of NginxLogItem objects and returns
     * a String representing a 5x2 table. The first col is the resource,
     * and the second col is the value of the number of requests.
     *
     * @param nginxLogItems List of NginxLogItem objects to analyze.
     * @return A String representing a 5x2 table.
     */
    public static String analyze(List<NginxLogItem> nginxLogItems) {
        Map<String, String> top5resources = nginxLogItems.stream()
            .map(NginxLogItem::request)
            .map(MostFrequentResourcesAnalytic::parseResourceFromRequest)
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
        top5resources.forEach((resource, count) ->
            builder.append(create1x2row(resource, count))
        );

        return builder.toString();
    }

    private static String parseResourceFromRequest(String request) {
        String[] tokens = request.split(" ");
        return (tokens.length == NUMBER_OF_TOKENS)
            ? tokens[1]
            : null;
    }
}

