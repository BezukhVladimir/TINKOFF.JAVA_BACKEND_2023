package edu.project3;

import edu.project3.logsanalyzers.nginx.NginxLogsAnalyzer;
import edu.project3.logsreporter.reports.AbstractReport;
import static edu.project3.logstypemanager.LogsTypeManager.getNginxLogsAnalyzer;

public final class Main {
    private Main() {
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    public static void main(String[] args) {
        NginxLogsAnalyzer nginxLogAnalyzer = getNginxLogsAnalyzer(args);
        AbstractReport result = nginxLogAnalyzer.fullyAnalyze();
        System.out.println(result.getReport());
    }
}
