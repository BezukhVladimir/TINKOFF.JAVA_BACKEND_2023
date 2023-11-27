package edu.project3.logstypemanager;

import edu.project3.logsanalyzers.nginx.NginxLogsAnalyzer;

public final class LogsTypeManager {
    private LogsTypeManager() {
    }

    public static NginxLogsAnalyzer getNginxLogsAnalyzer(String[] args) {
        return new NginxLogsAnalyzer(args);
    }
}
