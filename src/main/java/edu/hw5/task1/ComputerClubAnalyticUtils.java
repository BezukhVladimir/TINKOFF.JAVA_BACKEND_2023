package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class ComputerClubAnalyticUtils {
    private ComputerClubAnalyticUtils() {
    }

    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd, HH:mm";
    private final static String DATE_TIME_REGEX_PATTERN = "\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}";
    public final static String SESSION_TIME_REGEX_PATTERN
        = "^(" + DATE_TIME_REGEX_PATTERN + ") - (" + DATE_TIME_REGEX_PATTERN + ")$";

    public static Duration calculateAverageDurationOfSessions(String[] sessions) {
        var totalDuration = Stream.of(sessions)
            .map(ComputerClubAnalyticUtils::createSessionMatcher)
            .map(ComputerClubAnalyticUtils::createSessionDuration)
            .reduce(Duration.ZERO, Duration::plus);

        return totalDuration.dividedBy(sessions.length);
    }

    public static Matcher createSessionMatcher(String session) {
        var pattern = Pattern.compile(SESSION_TIME_REGEX_PATTERN);

        return pattern.matcher(session);
    }

    /**
     * Parses the creation of the Matcher object to extract the start and end session times,
     * then calculates the duration between them.
     *
     * @param matcher a Matcher object created by {@link #createSessionMatcher(String)}
     *                containing session time data.
     * @return a Duration object representing the time elapsed between the start and end of the session.
     */
    public static Duration createSessionDuration(Matcher matcher) {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        if (matcher.find()) {
            var startSessionTime = LocalDateTime.parse(matcher.group(1), formatter);
            var endSessionTime   = LocalDateTime.parse(matcher.group(2), formatter);

            return Duration.between(startSessionTime, endSessionTime);
        } else {
            throw new IllegalArgumentException("The provided matcher doesn't contain valid session time data.");
        }
    }

    public static String convertDurationToString(Duration duration) {
        var hours   = duration.toHours();
        var minutes = duration.minusHours(hours).toMinutes();

        return hours + "ч " + minutes + "м";
    }
}
