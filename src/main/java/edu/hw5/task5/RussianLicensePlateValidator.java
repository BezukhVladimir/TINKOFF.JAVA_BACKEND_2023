package edu.hw5.task5;

public final class RussianLicensePlateValidator {
    private RussianLicensePlateValidator() {
    }

    private final static String
        RUSSIAN_PERSONAL_TRANSPORT_LICENSE_PLATE_REGEX_PATTERN
        = "[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}";

    public static boolean isValidRussianPersonalTransportLicensePlate(String licensePlate) {
        return licensePlate.matches(RUSSIAN_PERSONAL_TRANSPORT_LICENSE_PLATE_REGEX_PATTERN);
    }
}
