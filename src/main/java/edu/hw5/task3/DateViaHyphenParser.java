package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class DateViaHyphenParser extends DateParser {
    /**
     * Local date examples:
     * <p>2020-10-10
     * <p>2020-12-2
     */
    private final static String LOCAL_DATE_PATTERN = "y-M-d";
    private final static DateTimeFormatter LOCAL_DATE = DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN);

    @Override
    public Optional<LocalDate> parse(String date) {
        try {
            return Optional.of(parseDateViaHyphen(date));
        } catch (Exception e) {
            return parseNext(date);
        }
    }

    private LocalDate parseDateViaHyphen(String date) {
        return LocalDate.parse(date, LOCAL_DATE);
    }
}
