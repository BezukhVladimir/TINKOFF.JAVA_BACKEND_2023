package edu.hw05.task5;

public final class RussianLicensePlateValidator {
    private RussianLicensePlateValidator() {
    }

    /**
     * Valid examples:
     * <p>А123ВЕ777
     * <p>О777ОО177
     * <p>
     * Invalid examples:
     * <p>123АВЕ777
     * <p>А123ВГ77
     * <p>А123ВЕ7777
     */
    private static final String
        RUSSIAN_PERSONAL_TRANSPORT_LICENSE_PLATE_REGEX_PATTERN
        = "[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}";

    public static boolean isValidRussianPersonalTransportLicensePlate(String licensePlate) {
        return licensePlate.matches(RUSSIAN_PERSONAL_TRANSPORT_LICENSE_PLATE_REGEX_PATTERN);
    }
}
