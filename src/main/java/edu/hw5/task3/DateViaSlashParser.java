package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class DateViaSlashParser extends DateParser {
    private final static String LOCAL_DATE_PATTERN = "d/M/y";

    @Override
    public Optional<LocalDate> parse(String date) {
        try {
            return Optional.of(parseDateViaSlash(date));
        } catch (Exception e) {
            return parseNext(date);
        }
    }

    public LocalDate parseDateViaSlash(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN));
    }
}
