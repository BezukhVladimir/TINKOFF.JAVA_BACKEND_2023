package edu.hw10.task2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.nio.file.StandardOpenOption.CREATE;

public final class CacheHandler implements InvocationHandler {
    private final Object target;
    private final Path cacheFile;
    private Map<String, HashMap<List<Object>, Object>> runtimeCache = new HashMap<>();

    public CacheHandler(Object target, Path temporaryDirectory) {
        this.target = target;
        this.cacheFile = temporaryDirectory.resolve("cache.txt");

        for (var method : target.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Cache.class)) {
                runtimeCache.putIfAbsent(method.getName(), new HashMap<>());
            }
        }

        load();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] argsArray) throws Throwable {
        method.setAccessible(true);

        List<Object> args = Arrays.asList(argsArray);
        if (method.isAnnotationPresent(Cache.class)) {
            var results = runtimeCache.get(method.getName());

            if (results.containsKey(args)) {
                return results.get(args);
            }

            Object result = method.invoke(target, argsArray);
            results.put(args, result);

            save();
            return result;
        }

        return method.invoke(target, argsArray);
    }

    private void save() {
        Map<String, HashMap<List<Object>, Object>> persistCache = new HashMap<>();

        for (var method : target.getClass().getDeclaredMethods()) {
            if (shouldPersist(method)) {
                persistCache.put(method.getName(), runtimeCache.get(method.getName()));
            }
        }

        if (!persistCache.isEmpty()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(cacheFile, CREATE))) {
                oos.writeObject(persistCache);
            } catch (IOException e) {
                throw new RuntimeException("Failed to write persistCache to file: " + cacheFile, e);
            }
        }
    }

    private void load() {
        try {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(cacheFile))) {
                Object obj = ois.readObject();
                if (obj instanceof Map) {
                    runtimeCache = (Map<String, HashMap<List<Object>, Object>>) obj;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException ignored) {
        }
    }

    private boolean shouldPersist(Method method) {
        Cache persistAnnotation = method.getAnnotation(Cache.class);
        return persistAnnotation != null && persistAnnotation.persist();
    }
}
