package edu.hw05.task4;

import java.util.regex.Pattern;

public final class PasswordValidator {
    private PasswordValidator() {
    }

    private final static String SPECIAL_CHARACTERS = "~!@#$%^&*|";
    public static final Pattern SPECIAL_CHARACTER_CLASS = Pattern.compile("[" + SPECIAL_CHARACTERS + "]");

    public static boolean containsSpecialCharacter(String password) {
        var matcher = SPECIAL_CHARACTER_CLASS.matcher(password);

        return matcher.find();
    }
}
