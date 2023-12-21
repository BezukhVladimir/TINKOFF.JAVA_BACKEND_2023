package edu.hw06.task6;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;

@Disabled
class PortScannerTest {
    @Test
    void scanPorts() {
        assertThatCode(PortScanner::scanPorts).doesNotThrowAnyException();
    }
}
