package edu.hw8.task3;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.concurrent.Executors;

@Disabled
public class PerformanceTest {
    private static final Map<String, String> MD5HASH_LOGIN_DATABASE = Map.of(
        "81dc9bdb52d04dc20036dbd8313ed055", "1234",
        "d077f244def8a70e5ea758bd8352fcd8", "cat",
        "7b38fd223a3e15a96f9b662e150c6aff", "0az9"
    );
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MAX_PASSWORD_LENGTH = 4;
    private static final int START_THREADS_NUMBER = 2;
    private static final int FINISH_THREADS_NUMBER = 6;
    private static final int THREADS_NUMBER_SHIFT = 2;

    @Test
    public void performanceTests() {
        double singleThread = singleThreadVersion();

        for (int threadsNumber = START_THREADS_NUMBER;
             threadsNumber <= FINISH_THREADS_NUMBER;
             threadsNumber += THREADS_NUMBER_SHIFT
        ) {
            double multiThread = multiThreadVersion(threadsNumber);
            printResultComparison(singleThread, multiThread, threadsNumber);
        }
    }

    private double singleThreadVersion() {
        var passwordMiner = new PasswordMiner(
            MD5HASH_LOGIN_DATABASE,
            ALPHABET,
            MAX_PASSWORD_LENGTH
        );

        var startTime = System.nanoTime();
        passwordMiner.singleThreadedPasswordMiner();
        var endTime = System.nanoTime();

        return (double) (endTime - startTime) / 1_000_000_000.0;
    }

    private double multiThreadVersion(int threadsNumber) {
        var passwordMiner = new PasswordMiner(
            MD5HASH_LOGIN_DATABASE,
            ALPHABET,
            MAX_PASSWORD_LENGTH
        );
        var executorService = Executors.newFixedThreadPool(threadsNumber);

        var startTime = System.nanoTime();
        passwordMiner.multiThreadedPasswordMiner(
            executorService,
            threadsNumber
        );
        var endTime = System.nanoTime();

        return (double) (endTime - startTime) / 1_000_000_000.0;
    }

    private void printResultComparison(
        double singleThread,
        double multiThread,
        int threadsNumber
    ) {
        System.out.println(
            "Comparison results for " + threadsNumber + " threads:"
        );
        System.out.println(
            "Single thread: executing time in seconds — "
                + singleThread
        );
        System.out.println(
            "Multi thread: executing time in seconds — "
                + multiThread
        );

        double executionTimeDifference = Math.abs(multiThread - singleThread);
        double ratio = singleThread / multiThread;

        System.out.printf("Difference: %.5f seconds%n", executionTimeDifference);
        System.out.printf("Multi thread is %.2fx faster than single thread%n", ratio);

        System.out.println();
    }
}
