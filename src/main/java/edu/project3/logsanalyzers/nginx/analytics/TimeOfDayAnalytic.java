package edu.project3.logsanalyzers.nginx.analytics;

import edu.project3.logsparsers.nginx.format.NginxLogItem;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.project3.logsreporter.LogsReporter.create1x3row;

public final class TimeOfDayAnalytic {
    private TimeOfDayAnalytic() {
    }

    private static final double PERCENT100 = 100.0;

    /**
     * Analyzes the list of NginxLogItems objects and returns
     * a String representing a 5x3 table. The first col is the
     * time of day, the second col is the count,
     * and the third col is the percent.
     *
     * @param nginxLogItems List of NginxLogItem objects to analyze.
     * @return A String representing a 5x3 table.
     */
    public static String analyze(List<NginxLogItem> nginxLogItems) {
        Map<String, Long> statusCounts = nginxLogItems.stream()
            .collect(Collectors.groupingBy(
                TimeOfDayAnalytic::getTimeOfDayCategory,
                Collectors.counting()
            ));

        Locale locale = Locale.getDefault();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat df = new DecimalFormat("#.##", symbols);

        var builder = new StringBuilder();
        for (Map.Entry<String, Long> entry : statusCounts.entrySet()) {
            String timeOfDay = entry.getKey();
            long count = entry.getValue();
            double percent = (count * PERCENT100) / nginxLogItems.size();

            builder.append(create1x3row(timeOfDay, Long.toString(count), df.format(percent)));
        }

        return builder.toString();
    }

    @SuppressWarnings({"MagicNumber", "ReturnCount"})
    private static String getTimeOfDayCategory(NginxLogItem nginxLogItem) {
        int hour = nginxLogItem.timeLocal().getHour();
        if (hour >= 6 && hour < 12) {
            return "Morning";
        } else if (hour >= 12 && hour < 18) {
            return "Afternoon";
        } else if (hour >= 18) {
            return "Evening";
        } else {
            return "Night";
        }
    }
}
