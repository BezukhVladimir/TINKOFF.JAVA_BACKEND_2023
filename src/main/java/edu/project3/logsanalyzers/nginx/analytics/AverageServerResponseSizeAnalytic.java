package edu.project3.logsanalyzers.nginx.analytics;

import edu.project3.logsparsers.nginx.format.NginxLogItem;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import static edu.project3.logsreporter.LogsReporter.create1x2row;

public final class AverageServerResponseSizeAnalytic {
    private AverageServerResponseSizeAnalytic() {
    }

    private static final String AVERAGE_SERVER_RESPONSE = "Average server response";

    /**
     * Analyzes the list of NginxLogItem objects and returns
     * a String representing a 1x2 table. The first col is the header,
     * and the second col is the value of the average server response time.
     *
     * @param nginxLogItems List of NginxLogItem objects to analyze.
     * @return A String representing a 1x2 table.
     */
    public static String analyze(List<NginxLogItem> nginxLogItems) {
        double averageResponse = nginxLogItems.stream()
            .mapToLong(NginxLogItem::bodyBytesSent)
            .average()
            .orElse(0.0);

        Locale locale = Locale.forLanguageTag("ru-RU");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        String formattedValue = df.format(averageResponse);

        return create1x2row(AVERAGE_SERVER_RESPONSE, formattedValue);
    }
}
