package edu.project3.logsprovider.sources;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

public final class PathMatcherSource implements AbstractSource {
    private final PathMatcher pathMatcher;

    public PathMatcherSource(String pattern) {
        String syntaxAndPattern = "glob:**/" + pattern;
        pathMatcher = FileSystems.getDefault().getPathMatcher(syntaxAndPattern);
    }

    public PathMatcher getPathMatcher() {
        return pathMatcher;
    }
}
