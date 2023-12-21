package edu.hw06.task3;

import java.nio.file.Files;

public interface IsWritableFilter extends AbstractFilter {
    static AbstractFilter writable() {
        return Files::isWritable;
    }
}
