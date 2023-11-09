package edu.hw5.task4;

import java.util.regex.Pattern;

public final class PasswordValidator {
    private PasswordValidator() {
    }

    private final static String SPECIAL_CHARACTERS = "~!@#$%^&*|";

    public static boolean containsSpecialCharacter(String password) {
        var pattern = Pattern.compile("[" + SPECIAL_CHARACTERS + "]");
        var matcher = pattern.matcher(password);

        return matcher.find();
    }
}
