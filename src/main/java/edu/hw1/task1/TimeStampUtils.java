package edu.hw1.task1;

public final class TimeStampUtils {

    private TimeStampUtils() {
    }

    private static final int SECONDS_PER_MINUTE = 60;

    /**
     * Parses a time string in the "mm:ss" format and returns the total duration in seconds.
     *
     * @param timestamp A string representing time in the "mm:ss" format.
     * @return The total duration in seconds, or -1 if the input is invalid.
     */
    public static int minutesToSeconds(String timestamp) {
        if (timestamp == null
            || timestamp.isEmpty()
            || !timestamp.matches("\\d+:\\d+")) {
            return -1;
        }

        String[] parts = timestamp.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);

        if (minutes < 0 || seconds >= SECONDS_PER_MINUTE) {
            return -1;
        }

        return minutes * SECONDS_PER_MINUTE + seconds;
    }
}
