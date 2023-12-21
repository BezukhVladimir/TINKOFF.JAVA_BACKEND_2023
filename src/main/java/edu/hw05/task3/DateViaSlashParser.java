package edu.hw05.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class DateViaSlashParser extends DateParser {
    /**
     * Local date examples:
     * <p>1/3/1976
     * <p>1/3/20
     */
    private static final String LOCAL_DATE_PATTERN = "d/M/y";
    private static final DateTimeFormatter LOCAL_DATE = DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN);

    @Override
    public Optional<LocalDate> parse(String date) {
        try {
            return Optional.of(parseDateViaSlash(date));
        } catch (Exception e) {
            return parseNext(date);
        }
    }

    public LocalDate parseDateViaSlash(String date) {
        return LocalDate.parse(date, LOCAL_DATE);
    }
}
