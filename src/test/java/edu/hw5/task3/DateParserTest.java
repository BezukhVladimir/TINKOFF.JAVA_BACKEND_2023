package edu.hw5.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public final class DateParserTest {
    private DateParser dateParser;
    @BeforeEach
    void createChainOfResponsibility() {
        dateParser = DateParser.link(
            new DateViaHyphenParser(),
            new DateViaSlashParser(),
            new DateInNaturalLanguageParser(),
            new DateInDaysAgoParser()
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "2020-10-10",
        "2020-12-2",
        "1/3/1976",
        "1/3/20",
        "tomorrow",
        "today",
        "yesterday",
        "1 day ago",
        "2234 days ago"
    })
    void testSuccessfulParse(String date) {
        // Act
        Optional<LocalDate> result = dateParser.parse(date);

        // Assert
        assertThat(result).isPresent().containsInstanceOf(LocalDate.class);
    }

    @Test
    void testUnsuccessfulParse() {
        // Arrange
        String unknownDateFormat = "54 years ago";

        // Act
        Optional<LocalDate> result = dateParser.parse(unknownDateFormat);

        // Assert
        assertThat(result).isNotPresent();
    }
}
