package edu.hw05.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class Friday13thsUtils {
    private Friday13thsUtils() {
    }

    private final static int DAY_13TH = 13;

    public static List<LocalDate> findAllFriday13thsInYear(int year) {
        List<LocalDate> friday13ths = new ArrayList<>();

        LocalDate current = LocalDate.of(year, Month.JANUARY, DAY_13TH);
        while (current.getYear() == year) {
            if (current.getDayOfWeek() == DayOfWeek.FRIDAY) {
                friday13ths.add(current);
            }

            current = current.plusMonths(1);
        }

        return friday13ths;
    }

    public static LocalDate findNextFriday13th(LocalDate current) {
        TemporalAdjuster nextFriday = TemporalAdjusters.next(DayOfWeek.FRIDAY);

        LocalDate next = current.with(nextFriday);
        while (next.getDayOfMonth() != DAY_13TH) {
            next = next.with(nextFriday);
        }

        return next;
    }
}
