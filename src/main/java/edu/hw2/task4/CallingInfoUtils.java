package edu.hw2.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CallingInfoUtils {
    private final static Logger LOGGER = LogManager.getLogger();

    private CallingInfoUtils() {
    }

    public static CallingInfo callingInfo() {
        var callerInfo = new Throwable().getStackTrace()[1];
        return new CallingInfo(callerInfo.getClassName(), callerInfo.getMethodName());
    }

    public record CallingInfo(String className, String methodName) {}
}
