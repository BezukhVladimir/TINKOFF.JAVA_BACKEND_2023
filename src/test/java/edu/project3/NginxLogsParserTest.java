package edu.project3;

import edu.project3.logsparsers.nginx.format.NginxLogItem;
import org.junit.jupiter.api.Test;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import static edu.project3.logsparsers.nginx.NginxLogsParser.parseLogLines;
import static org.assertj.core.api.Assertions.assertThat;

class NginxLogsParserTest {
    private static final List<String> NGINX_LOG_LINES = List.of(
        "93.180.71.3 - - [17/May/2015:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
        "80.91.33.133 - - [17/May/2015:08:05:24 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)\"",
        "217.168.17.5 - - [17/May/2015:08:05:34 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 490 \"-\" \"Debian APT-HTTP/1.3 (0.8.10.3)\""
    );
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH
    );

    @Test
    void testParseLogLines() throws UnknownHostException {
        // Arrange
        List<NginxLogItem> expected = List.of(
            NginxLogItem.builder()
                .setRemoteAddress(InetAddress.getByName("93.180.71.3"))
                .setRemoteUser("-")
                .setTimeLocal(OffsetDateTime.parse("17/May/2015:08:05:23 +0000", DATE_TIME_FORMATTER))
                .setRequest("GET /downloads/product_1 HTTP/1.1")
                .setStatus(304)
                .setBodyBytesSent(0L)
                .setHttpReferer(URI.create("-"))
                .setHttpUserAgent("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)")
                .build(),
            NginxLogItem.builder()
                .setRemoteAddress(InetAddress.getByName("80.91.33.133"))
                .setRemoteUser("-")
                .setTimeLocal(OffsetDateTime.parse("17/May/2015:08:05:24 +0000", DATE_TIME_FORMATTER))
                .setRequest("GET /downloads/product_1 HTTP/1.1")
                .setStatus(304)
                .setBodyBytesSent(0L)
                .setHttpReferer(URI.create("-"))
                .setHttpUserAgent("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)")
                .build(),
            NginxLogItem.builder()
                .setRemoteAddress(InetAddress.getByName("217.168.17.5"))
                .setRemoteUser("-")
                .setTimeLocal(OffsetDateTime.parse("17/May/2015:08:05:34 +0000", DATE_TIME_FORMATTER))
                .setRequest("GET /downloads/product_1 HTTP/1.1")
                .setStatus(200)
                .setBodyBytesSent(490L)
                .setHttpReferer(URI.create("-"))
                .setHttpUserAgent("Debian APT-HTTP/1.3 (0.8.10.3)")
                .build()
        );

        // Act
        List<NginxLogItem> result = parseLogLines(NGINX_LOG_LINES);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
