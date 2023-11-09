package edu.hw5.task1;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.regex.Matcher;
import static edu.hw5.task1.ComputerClubAnalyticUtils.calculateAverageDurationOfSessions;
import static edu.hw5.task1.ComputerClubAnalyticUtils.convertDurationToString;
import static edu.hw5.task1.ComputerClubAnalyticUtils.createSessionMatcher;
import static edu.hw5.task1.ComputerClubAnalyticUtils.createSessionDuration;
import static org.assertj.core.api.Assertions.assertThat;

public final class ComputerClubAnalyticUtilsTest {
    @Test
    void testCreateSessionDuration() {
        // Arrange
        String sessionTime = "2022-03-12, 20:20 - 2022-03-12, 23:50";
        Duration expected = Duration.ofHours(3).plusMinutes(30);

        // Act
        Matcher matcher = createSessionMatcher(sessionTime);
        Duration result = createSessionDuration(matcher);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testCalculateAverageDurationOfSessions() {
        // Arrange
        String[] sessions = {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };
        Duration expected = Duration.ofHours(3).plusMinutes(40);

        // Act
        Duration result = calculateAverageDurationOfSessions(sessions);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testConvertDurationToString() {
        // Arrange
        String[] sessions = { "2000-02-22, 00:00 - 2023-11-08, 00:00" };
        String expected = "207840ч 0м";

        // Act
        Duration duration = calculateAverageDurationOfSessions(sessions);
        String result = convertDurationToString(duration);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
