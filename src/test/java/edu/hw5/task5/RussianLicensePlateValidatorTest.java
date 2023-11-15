package edu.hw5.task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw5.task5.RussianLicensePlateValidator.isValidRussianPersonalTransportLicensePlate;
import static org.assertj.core.api.Assertions.assertThat;

public final class RussianLicensePlateValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {
        "А123ВЕ777",
        "О777ОО177"
    })
    void testValidRussianPersonalTransportLicensePlate(String validLicensePlate) {
        // Act
        boolean result = isValidRussianPersonalTransportLicensePlate(validLicensePlate);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777"
    })
    void testInvalidRussianPersonalTransportLicensePlate(String invalidLicensePlate) {
        // Act
        boolean result = isValidRussianPersonalTransportLicensePlate(invalidLicensePlate);

        // Assert
        assertThat(result).isFalse();
    }
}
