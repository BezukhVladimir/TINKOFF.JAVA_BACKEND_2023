package edu.hw05.task1;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.regex.Matcher;
import static org.assertj.core.api.Assertions.assertThat;

public final class ComputerClubAnalyticUtilsTest {
    @Test
    void createSessionDuration() {
        // Arrange
        String sessionTime = "2022-03-12, 20:20 - 2022-03-12, 23:50";
        Duration expected = Duration.ofHours(3).plusMinutes(30);

        // Act
        Matcher matcher = ComputerClubAnalyticUtils.createSessionMatcher(sessionTime);
        Duration result = ComputerClubAnalyticUtils.createSessionDuration(matcher);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateAverageDurationOfSessions() {
        // Arrange
        String[] sessions = {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };
        Duration expected = Duration.ofHours(3).plusMinutes(40);

        // Act
        Duration result = ComputerClubAnalyticUtils.calculateAverageDurationOfSessions(sessions);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void convertDurationToString() {
        // Arrange
        String[] sessions = { "2000-02-22, 00:00 - 2023-11-08, 00:00" };
        String expected = "207840ч 0м";

        // Act
        Duration duration = ComputerClubAnalyticUtils.calculateAverageDurationOfSessions(sessions);
        String result = ComputerClubAnalyticUtils.convertDurationToString(duration);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
