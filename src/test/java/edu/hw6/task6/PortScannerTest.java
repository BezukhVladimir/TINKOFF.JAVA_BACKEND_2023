package edu.hw6.task6;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;

@Disabled
public class PortScannerTest {
    @Test
    void testScanPorts() {
        assertThatCode(PortScanner::scanPorts).doesNotThrowAnyException();
    }
}
