package edu.hw06.task3;

import java.nio.file.Files;

public interface MagicNumberFilter extends AbstractFilter {
    static AbstractFilter magicNumber(byte... bytes) {
        return path -> {
            byte[] fileBytes = Files.readAllBytes(path);

            if (bytes.length > fileBytes.length) {
                return false;
            }

            for (int i = 0; i < bytes.length; ++i) {
                if (fileBytes[i] != bytes[i]) {
                    return false;
                }
            }

            return true;
        };
    }
}
