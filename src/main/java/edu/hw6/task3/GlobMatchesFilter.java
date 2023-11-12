package edu.hw6.task3;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

public interface GlobMatchesFilter extends AbstractFilter {
    static AbstractFilter globMatches(String pattern) {
        PathMatcher matcher = FileSystems
            .getDefault()
            .getPathMatcher("glob:" + pattern);

        return path -> matcher.matches(path.getFileName());
    }
}
