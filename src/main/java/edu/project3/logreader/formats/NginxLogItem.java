package edu.project3.logreader.formats;

import java.time.OffsetDateTime;

public record NginxLogItem(
    String remoteAddress,
    String remoteUser,
    OffsetDateTime timeLocal,
    String request,
    int status,
    long bodyBytesSent,
    String httpReferer,
    String userAgent
) implements AbstractLogItem {
}
