package edu.project3.logsanalyzers.nginx.analytics;

import edu.project3.logsparsers.nginx.format.NginxLogItem;
import java.util.List;
import static edu.project3.logsreporter.LogsReporter.create1x2row;

public final class TotalRequestsAnalytic {
    private TotalRequestsAnalytic() {
    }

    private static final String TOTAL_REQUESTS = "Total requests";

    /**
     * Analyzes the list of NginxLogItem objects and returns
     * a String representing a 1x2 table. The first col is the header,
     * and the second col is the value of the total requests.
     *
     * @param nginxLogItems List of NginxLogItem objects to analyze.
     * @return A String representing a 1x2 table.
     */
    public static String analyze(List<NginxLogItem> nginxLogItems) {
        return create1x2row(TOTAL_REQUESTS, Integer.toString(nginxLogItems.size()));
    }
}
