package edu.project3.loganalyzer.configurator.configurations;

import edu.project3.logreporter.LogReporter;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public record NginxConfiguration(
    List<Path> paths,
    LocalDate from,
    LocalDate to,
    LogReporter.ReportType format
) implements AbstractConfiguration {
}
