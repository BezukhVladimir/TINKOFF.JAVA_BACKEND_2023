package edu.project3.logsreporter.reports;

public abstract class AbstractReport {
    private final String report;

    public AbstractReport(String report) {
        this.report = report;
    }

    public String getReport() {
        return report;
    }
}
