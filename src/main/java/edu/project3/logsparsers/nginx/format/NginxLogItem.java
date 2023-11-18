package edu.project3.logsparsers.nginx.format;

import java.net.InetAddress;
import java.net.URI;
import java.time.OffsetDateTime;

public record NginxLogItem(
    InetAddress remoteAddress,
    String remoteUser,
    OffsetDateTime timeLocal,
    String request,
    int status,
    long bodyBytesSent,
    URI httpReferer,
    String httpUserAgent
) {
}
