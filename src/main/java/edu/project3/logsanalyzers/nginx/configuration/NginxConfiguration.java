package edu.project3.logsanalyzers.nginx.configuration;

import edu.project3.logsprovider.sources.AbstractSource;
import edu.project3.logsprovider.sources.PathMatcherSource;
import edu.project3.logsprovider.sources.URLSource;
import edu.project3.logsreporter.LogsReporter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import picocli.CommandLine;

@CommandLine.Command(
    name = "Nginx log files analysis",
    description = "Return nginx log files analysis report."
) public class NginxConfiguration {
    public NginxConfiguration() {
    }

    @CommandLine.Option(
        names = {"--path"},
        description = "Path to one or more NGINX log files in form of a local template or URL.",
        required = true
    ) private AbstractSource source;
    @CommandLine.Option(
        names = {"--from"},
        description = "Optional time parameter from in ISO-8601 format, such as '2000-02-22T00:00:00+00:00'."
    ) private OffsetDateTime from;
    @CommandLine.Option(
        names = {"--to"},
        description = "Optional time parameter to in ISO-8601 format, , such as '2000-02-22T00:00:00+00:00'."
    ) private OffsetDateTime to;
    @CommandLine.Option(
        names = {"--format"},
        description = "Optional argument of output format of result."
    ) private LogsReporter.ReportFormat format;

    public void setSource(AbstractSource source) {
        this.source = source;
    }

    public void setFrom(OffsetDateTime from) {
        this.from = from;
    }

    public void setTo(OffsetDateTime to) {
        this.to = to;
    }

    public void setFormat(LogsReporter.ReportFormat format) {
        this.format = format;
    }

    public AbstractSource getSource() {
        return source;
    }

    public OffsetDateTime getFrom() {
        return from;
    }

    public OffsetDateTime getTo() {
        return to;
    }

    public LogsReporter.ReportFormat getFormat() {
        return format;
    }

    public static AbstractSource convert(String pattern) {
        try {
            return new URLSource(pattern);
        } catch (URISyntaxException | IllegalArgumentException | MalformedURLException ignored) {
            return new PathMatcherSource(pattern);
        }
    }
}
