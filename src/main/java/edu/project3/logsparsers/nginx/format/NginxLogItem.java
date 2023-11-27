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
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private InetAddress remoteAddress;
        private String remoteUser;
        private OffsetDateTime timeLocal;
        private String request;
        private int status;
        private long bodyBytesSent;
        private URI httpReferer;
        private String httpUserAgent;

        public Builder setRemoteAddress(InetAddress remoteAddress) {
            this.remoteAddress = remoteAddress;
            return this;
        }

        public Builder setRemoteUser(String remoteUser) {
            this.remoteUser = remoteUser;
            return this;
        }

        public Builder setTimeLocal(OffsetDateTime timeLocal) {
            this.timeLocal = timeLocal;
            return this;
        }

        public Builder setRequest(String request) {
            this.request = request;
            return this;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setBodyBytesSent(long bodyBytesSent) {
            this.bodyBytesSent = bodyBytesSent;
            return this;
        }

        public Builder setHttpReferer(URI httpReferer) {
            this.httpReferer = httpReferer;
            return this;
        }

        public Builder setHttpUserAgent(String httpUserAgent) {
            this.httpUserAgent = httpUserAgent;
            return this;
        }

        public NginxLogItem build() {
            return new NginxLogItem(
                remoteAddress,
                remoteUser,
                timeLocal,
                request,
                status,
                bodyBytesSent,
                httpReferer,
                httpUserAgent
            );
        }
    }
}
