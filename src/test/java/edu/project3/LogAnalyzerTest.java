package edu.project3;

import edu.project3.logsanalyzers.nginx.NginxLogsAnalyzer;
import edu.project3.logsreporter.reports.AbstractReport;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static edu.project3.logstypemanager.LogsTypeManager.getNginxLogsAnalyzer;
import static org.assertj.core.api.Assertions.assertThat;

class LogAnalyzerTest {

    private static final String CORRECT_MARKDOWN_REPORT = "src/test/java/edu/project3/reports/correctMarkdownReport.md";
    private static final String CORRECT_ADOC_REPORT = "src/test/java/edu/project3/reports/correctAdocReport.adoc";

    @Test
    void markdownReport() {
        // Arrange
        String[] args = {
            "--path", "logs/test*"
        };
        String expected;
        try {
            expected = Files.readString(Paths.get(CORRECT_MARKDOWN_REPORT));
        } catch (IOException ignored) {
            expected = "";
        }

        // Act
        NginxLogsAnalyzer nginxLogAnalyzer = getNginxLogsAnalyzer(args);
        AbstractReport result = nginxLogAnalyzer.fullyAnalyze();

        // Assert
        assertThat(result.getReport()).isEqualTo(expected);
    }

    @Test
    void adocReport() {
        // Arrange
        String[] args = {
            "--path", "logs/test*",
            "--format", "adoc"
        };
        String expected;
        try {
            expected = Files.readString(Paths.get(CORRECT_ADOC_REPORT));
        } catch (IOException ignored) {
            expected = "";
        }

        // Act
        NginxLogsAnalyzer nginxLogAnalyzer = getNginxLogsAnalyzer(args);
        AbstractReport result = nginxLogAnalyzer.fullyAnalyze();

        // Assert
        assertThat(result.getReport()).isEqualTo(expected);
    }
}
