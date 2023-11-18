package edu.project3.logsanalyzers.nginx;

import edu.project3.logsanalyzers.nginx.analytics.AverageServerResponseSizeAnalytic;
import edu.project3.logsanalyzers.nginx.analytics.HttpStatusAnalytic;
import edu.project3.logsanalyzers.nginx.analytics.MostFrequentResourcesAnalytic;
import edu.project3.logsanalyzers.nginx.analytics.MostFrequentResponseCodesAnalytic;
import edu.project3.logsanalyzers.nginx.analytics.TimeOfDayAnalytic;
import edu.project3.logsanalyzers.nginx.analytics.TotalRequestsAnalytic;
import edu.project3.logsanalyzers.nginx.configuration.NginxConfiguration;
import edu.project3.logsparsers.nginx.format.NginxLogItem;
import edu.project3.logsprovider.sources.AbstractSource;
import edu.project3.logsprovider.sources.PathMatcherSource;
import edu.project3.logsprovider.sources.URLSource;
import edu.project3.logsreporter.LogsReporter;
import edu.project3.logsreporter.reports.AbstractReport;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import picocli.CommandLine;
import static edu.project3.logsparsers.nginx.NginxLogsParser.parseLogFiles;
import static edu.project3.logsparsers.nginx.NginxLogsParser.parseLogLines;
import static edu.project3.logsprovider.LogsProvider.findLogFilesInRootDirectory;
import static edu.project3.logsprovider.LogsProvider.readAllLinesFrom;
import static edu.project3.logsreporter.LogsReporter.create1x1row;
import static edu.project3.logsreporter.LogsReporter.create1x2row;
import static edu.project3.logsreporter.LogsReporter.create1x3row;
import static edu.project3.logsreporter.LogsReporter.createReport;

@SuppressWarnings({"MultipleStringLiterals"})
public class NginxLogsAnalyzer {
    private static final int NUMBER_OF_TABLES = 4;
    private NginxConfiguration configuration;
    private List<Path> allNginxLogFiles;
    private List<NginxLogItem> allNginxLogItems;

    public NginxLogsAnalyzer(String[] args) {
        configure(args);
        initLogItems();
    }

    public AbstractReport fullyAnalyze() {
        List<String> tables = new ArrayList<>(NUMBER_OF_TABLES);

        tables.add(getFileNamesNx1Table());
        tables.add(getMetricValue5x2Table());
        tables.add(getResourceCount6x2Table());
        tables.add(getStatusNameCount6x3Table());
        tables.add(getCategoryCountPercent6x3Table());
        tables.add(getTimeOfDayCountPercent6x3Table());

        LogsReporter.ReportFormat format = configuration.getFormat();
        return format != null
            ? createReport(tables, format)
            : createReport(tables, LogsReporter.ReportFormat.markdown);
    }

    private void configure(String[] args) {
        configuration = new NginxConfiguration();
        CommandLine commandLine = new CommandLine(configuration);
        commandLine.registerConverter(AbstractSource.class, NginxConfiguration::convert);
        commandLine.parseArgs(args);
    }

    private void initLogItems() {
        allNginxLogFiles = new ArrayList<>();
        AbstractSource source = configuration.getSource();

        if (source instanceof URLSource urlSource) {
            allNginxLogFiles.add(Path.of(urlSource.getURL().getFile()));
            allNginxLogItems = parseLogLines(
                readAllLinesFrom(urlSource.getURL())
            );
        } else if (source instanceof PathMatcherSource pathMatcherSource) {
            allNginxLogFiles.addAll(findLogFilesInRootDirectory(pathMatcherSource.getPathMatcher()));
            allNginxLogItems = parseLogFiles(allNginxLogFiles);
        }

        filterNginxLogItemsByOffsetDateTimeRange();
    }

    private void filterNginxLogItemsByOffsetDateTimeRange() {
        allNginxLogItems = allNginxLogItems.stream()
            .filter(this::isNginxLogItemInDateRange)
            .toList();
    }

    private boolean isNginxLogItemInDateRange(NginxLogItem item) {
        OffsetDateTime timeLocal = item.timeLocal();
        OffsetDateTime from = configuration.getFrom();
        OffsetDateTime to   = configuration.getTo();

        return (from == null || timeLocal.isAfter(from)) && (to == null || timeLocal.isBefore(to));
    }

    private String getFileNamesNx1Table() {
        return create1x1row("File name") + getFileNames();
    }

    private String getFileNames() {
        var builder = new StringBuilder();

        for (var path : allNginxLogFiles) {
            builder.append(create1x1row(path.getFileName().toString()));
        }

        return builder.toString();
    }

    private String getMetricValue5x2Table() {
        var info = new StringBuilder();

        String from = configuration.getFrom() != null ? configuration.getFrom().toString() : "-";
        String to   = configuration.getTo() != null ? configuration.getTo().toString() : "-";

        info.append(create1x2row("Metric", "Value"))
            .append(create1x2row("From date", from))
            .append(create1x2row("To date", to))
            .append(TotalRequestsAnalytic.analyze(allNginxLogItems))
            .append(AverageServerResponseSizeAnalytic.analyze(allNginxLogItems));

        return info.toString();
    }

    private String getResourceCount6x2Table() {
        return create1x2row("Resource", "Count")
            + MostFrequentResourcesAnalytic.analyze(allNginxLogItems);
    }

    private String getStatusNameCount6x3Table() {
        return create1x3row("Status", "Name", "Count")
            + MostFrequentResponseCodesAnalytic.analyze(allNginxLogItems);
    }

    private String getCategoryCountPercent6x3Table() {
        return create1x3row("Category", "Count", "Percent")
            + HttpStatusAnalytic.analyze(allNginxLogItems);
    }

    private String getTimeOfDayCountPercent6x3Table() {
        return create1x3row("Time of day", "Count", "Percent")
            + TimeOfDayAnalytic.analyze(allNginxLogItems);
    }
}
