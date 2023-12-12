package edu.hw10.task2;

import java.lang.reflect.Proxy;
import java.nio.file.Path;

public final class CacheProxy implements AutoCloseable {
    private CacheHandler handler;

    private CacheProxy() {
    }

    public static <T> T create(T target, Class<? extends T> targetClass, Path temporaryDirectory) {
        return (T) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            targetClass.getInterfaces(),
            new CacheHandler(target, temporaryDirectory)
        );
    }

    @Override
    public void close() throws Exception {
    }
}
