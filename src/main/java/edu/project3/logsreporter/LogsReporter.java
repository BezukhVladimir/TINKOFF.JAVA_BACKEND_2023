package edu.project3.logsreporter;

import edu.project3.logsreporter.reports.AbstractReport;
import edu.project3.logsreporter.reports.AdocReport;
import edu.project3.logsreporter.reports.MarkdownReport;
import java.util.List;

public final class LogsReporter {
    private LogsReporter() {
    }

    public static final String TABLE_COL_SPLITTER = ">-<";
    public static final String TABLE_ROW_SPLITTER = System.lineSeparator();
    private static final String ADOC_TABLE_SIGNATURE = "|===";

    public enum ReportFormat {
        markdown, adoc
    }

    public static AbstractReport createReport(List<String> tables, ReportFormat format) {
        return switch (format) {
            case adoc ->     createAdocReport(tables);
            case markdown -> createMarkdownReport(tables);
        };
    }

    public static String create1x1row(String col1) {
        return col1 + LogsReporter.TABLE_ROW_SPLITTER;
    }

    public static String create1x2row(String col1, String col2) {
        return col1
            + LogsReporter.TABLE_COL_SPLITTER
            + col2
            + LogsReporter.TABLE_ROW_SPLITTER;
    }

    public static String create1x3row(String col1, String col2, String col3) {
        return col1
            + LogsReporter.TABLE_COL_SPLITTER
            + col2
            + LogsReporter.TABLE_COL_SPLITTER
            + col3
            + LogsReporter.TABLE_ROW_SPLITTER;
    }

    private static AbstractReport createMarkdownReport(List<String> tables) {
        var report = new StringBuilder();

        for (String table : tables) {
            String[] rows = table.split(TABLE_ROW_SPLITTER);

            report.append(createMarkdownHeader(rows[0]));
            for (int row = 1; row < rows.length; ++row) {
                report.append(createMarkdownRow(rows[row]));
            }

            report.append(createSeparator());
        }

        return new MarkdownReport(report.toString());
    }

    private static AbstractReport createAdocReport(List<String> tables) {
        var report = new StringBuilder();

        for (String table : tables) {
            String[] rows = table.split(TABLE_ROW_SPLITTER);

            report.append(createAdocHeader(rows[0]));
            for (int row = 1; row < rows.length; ++row) {
                report.append(createAdocRow(rows[row]));
            }

            report.append(ADOC_TABLE_SIGNATURE).append(createSeparator());
        }

        return new AdocReport(report.toString());
    }

    private static String createMarkdownHeader(String header) {
        int cols = header.split(TABLE_COL_SPLITTER).length;
        return createMarkdownRow(header)
            + "|" + ":-:|".repeat(cols) + System.lineSeparator();
    }

    private static String createMarkdownRow(String row) {
        String[] cols = row.split(TABLE_COL_SPLITTER);
        return "|" + String.join("|", cols) + "|" + System.lineSeparator();
    }

    private static String createAdocHeader(String header) {
        int cols = header.split(TABLE_COL_SPLITTER).length;
        return "[cols=\"^" + ",^".repeat(cols - 1) + "\"]" + System.lineSeparator()
            + ADOC_TABLE_SIGNATURE + System.lineSeparator()
            + createAdocRow(header)
            + System.lineSeparator();
    }

    private static String createAdocRow(String row) {
        String[] cols = row.split(TABLE_COL_SPLITTER);
        return "|" + String.join("|", cols) + System.lineSeparator();
    }

    private static String createSeparator() {
        return System.lineSeparator() + System.lineSeparator();
    }
}
