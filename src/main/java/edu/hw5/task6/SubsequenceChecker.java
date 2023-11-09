package edu.hw5.task6;

import java.util.regex.Pattern;

public final class SubsequenceChecker {
    private SubsequenceChecker() {
    }

    public static boolean isSubsequence(String subsequence, String sequence) {
        var pattern = Pattern.compile(subsequence);
        var matcher = pattern.matcher(sequence);

        return matcher.find();
    }
}
