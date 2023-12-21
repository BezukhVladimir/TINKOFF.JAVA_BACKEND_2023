package edu.hw06.task3;

import java.util.regex.Pattern;

public interface RegexContainsFilter extends AbstractFilter {
    static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);

        return path -> pattern.matcher(path.toString()).find();
    }
}
