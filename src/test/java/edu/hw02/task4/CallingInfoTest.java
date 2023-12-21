package edu.hw02.task4;

import edu.hw02.task4.CallingInfoUtils.CallingInfo;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CallingInfoTest {
    @Test
    void sayMyName() {
        CallingInfo result = CallingInfoUtils.callingInfo();

        assertThat(result.methodName()).isEqualTo("sayMyName");
    }
}
