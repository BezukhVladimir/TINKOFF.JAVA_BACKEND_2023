package edu.hw05.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public final class DateInDaysAgoParser extends DateParser {
    /**
     * Days ago pattern examples:
     * <p>1 day ago
     * <p>2 days ago
     * <p>123 days ago
     */
    private static final String DATE_REGEX_PATTERN = "^(\\d+)\\s+day[s]?\\s+ago$";
    private static final Pattern DATE = Pattern.compile(DATE_REGEX_PATTERN);

    @Override
    public Optional<LocalDate> parse(String date) {
        try {
            return Optional.of(parseDateInDaysAgo(date));
        } catch (Exception e) {
            return parseNext(date);
        }
    }

    public LocalDate parseDateInDaysAgo(String date) {
        int daysAgo = parseDaysAgo(date);

        return LocalDate.now().minusDays(daysAgo);
    }

    private int parseDaysAgo(String date) {
        var matcher = DATE.matcher(date);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
