package edu.hw08.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int threadsNumber;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;
    private volatile boolean isShutdown;

    public static FixedThreadPool create(int threadsNumber) {
        return new FixedThreadPool(threadsNumber);
    }

    private FixedThreadPool(int threadsNumber) {
        this.threadsNumber = threadsNumber;
        this.threads       = new Thread[threadsNumber];
        this.tasks         = new LinkedBlockingQueue<>();
        this.isShutdown    = false;
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsNumber; ++i) {
            threads[i] = createThread();
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if (isShutdown) {
            throw new IllegalStateException();
        }

        try {
            tasks.put(command);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        isShutdown = true;

        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private Thread createThread() {
        return new Thread(new Executor());
    }

    private class Executor implements Runnable {
        @Override
        public void run() {
            while (shouldContinue()) {
                try {
                    Runnable task = tasks.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        private boolean shouldContinue() {
            return !Thread.currentThread().isInterrupted();
        }
    }
}
