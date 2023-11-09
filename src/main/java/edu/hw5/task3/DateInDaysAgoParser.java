package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public final class DateInDaysAgoParser extends DateParser {
    private final static String DATE_REGEX_PATTERN = "^(\\d+)\\s+day[s]?\\s+ago$";

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
        var pattern = Pattern.compile(DATE_REGEX_PATTERN);
        var matcher = pattern.matcher(date);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
