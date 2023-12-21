package edu.hw07.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCacheService implements PersonDatabase {
    private final Map<Integer, Person> idCache = new HashMap<>();
    private final Map<String, Person> nameCache = new HashMap<>();
    private final Map<String, Person> addressCache = new HashMap<>();
    private final Map<String, Person> phoneCache = new HashMap<>();
    private final ReadWriteLock rwLocker = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        rwLocker.writeLock().lock();

        try {
            idCache.put(person.id(), person);
            nameCache.put(person.name(), person);
            addressCache.put(person.address(), person);
            phoneCache.put(person.phoneNumber(), person);
        } finally {
            rwLocker.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        rwLocker.writeLock().lock();

        try {
            Person person = idCache.remove(id);

            if (person != null) {
                nameCache.remove(person.name());
                addressCache.remove(person.address());
                phoneCache.remove(person.phoneNumber());
            }
        } finally {
            rwLocker.writeLock().unlock();
        }
    }

    @Override
    public Person findByName(String name) {
        rwLocker.readLock().lock();

        try {
            return nameCache.get(name);
        } finally {
            rwLocker.readLock().unlock();
        }
    }

    @Override
    public Person findByAddress(String address) {
        rwLocker.readLock().lock();

        try {
            return addressCache.get(address);
        } finally {
            rwLocker.readLock().unlock();
        }
    }

    @Override
    public Person findByPhone(String phone) {
        rwLocker.readLock().lock();

        try {
            return phoneCache.get(phone);
        } finally {
            rwLocker.readLock().unlock();
        }
    }
}
