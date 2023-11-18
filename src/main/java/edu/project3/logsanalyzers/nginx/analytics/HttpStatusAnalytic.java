package edu.project3.logsanalyzers.nginx.analytics;

import edu.project3.logsparsers.nginx.format.NginxLogItem;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.project3.logsreporter.LogsReporter.create1x3row;

public final class HttpStatusAnalytic {
    private HttpStatusAnalytic() {
    }

    private static final double PERCENT100 = 100.0;

    /**
     * Analyzes the list of NginxLogItems objects and returns
     * a String representing a 5x3 table. The first col is the
     * category of the status code, the second col is the count,
     * and the third col is the percent.
     *
     * @param nginxLogItems List of NginxLogItem objects to analyze.
     * @return A String representing a 5x3 table.
     */
    public static String analyze(List<NginxLogItem> nginxLogItems) {
        Map<String, Long> statusCounts = nginxLogItems.stream()
            .collect(Collectors.groupingBy(
                HttpStatusAnalytic::getStatusCategory,
                Collectors.counting()
            ));

        Locale locale = Locale.getDefault();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat df = new DecimalFormat("#.##", symbols);

        var builder = new StringBuilder();
        for (Map.Entry<String, Long> entry : statusCounts.entrySet()) {
            String category = entry.getKey();
            long count = entry.getValue();
            double percent = (count * PERCENT100) / nginxLogItems.size();

            builder.append(create1x3row(category, Long.toString(count), df.format(percent)));
        }

        return builder.toString();
    }

    @SuppressWarnings({"MagicNumber", "ReturnCount"})
    private static String getStatusCategory(NginxLogItem nginxLogItem) {
        int statusCode = nginxLogItem.status();

        if (statusCode >= 100 && statusCode < 200) {
            return "Informational";
        } else if (statusCode >= 200 && statusCode < 300) {
            return "Successful";
        } else if (statusCode >= 300 && statusCode < 400) {
            return "Redirection";
        } else if (statusCode >= 400 && statusCode < 500) {
            return "Client Errors";
        } else if (statusCode >= 500 && statusCode < 600) {
            return "Server Errors";
        } else {
            return "Unknown";
        }
    }
}
