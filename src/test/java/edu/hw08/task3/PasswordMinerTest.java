package edu.hw08.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordMinerTest {
    private static final Map<String, String> MD5HASH_LOGIN_DATABASE = Map.of(
        "81dc9bdb52d04dc20036dbd8313ed055", "1234",
        "d077f244def8a70e5ea758bd8352fcd8", "cat",
        "7b38fd223a3e15a96f9b662e150c6aff", "0az9"
    );
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MAX_PASSWORD_LENGTH = 4;
    private static final int THREADS_NUMBER = 4;

    private PasswordMiner passwordMiner;

    @BeforeEach
    void createPasswordMiner() {
        passwordMiner = new PasswordMiner(
            MD5HASH_LOGIN_DATABASE,
            ALPHABET,
            MAX_PASSWORD_LENGTH
        );
    }

    @Test
    void singleThreadVersion() {
        // Act
        passwordMiner.singleThreadedPasswordMiner();
        ConcurrentHashMap<String, String> result = passwordMiner.getLoginPasswordDatabase();

        // Assert
        assertThat(result).contains(
            entry("1234", "1234"),
            entry("cat", "cat"),
            entry("0az9", "0az9")
        );
    }

    @Test
    void multiThreadVersion() throws InterruptedException {
        // Arrange
        var executorService = Executors.newFixedThreadPool(THREADS_NUMBER);

        // Act
        passwordMiner.multiThreadedPasswordMiner(
            executorService,
            THREADS_NUMBER
        );
        ConcurrentHashMap<String, String> result = passwordMiner.getLoginPasswordDatabase();

        // Assert
        assertThat(result).contains(
            entry("1234", "1234"),
            entry("cat", "cat"),
            entry("0az9", "0az9")
        );
    }
}
