package edu.hw2.task4;

import edu.hw2.task4.CallingInfoUtils.CallingInfo;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CallingInfoTest {
    @Test
    void testSayMyName() {
        CallingInfo result = CallingInfoUtils.callingInfo();

        assertThat(result.methodName()).isEqualTo("testSayMyName");
    }
}
