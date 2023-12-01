package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;

public class CacheService implements PersonDatabase {
    private final Map<Integer, Person> idCache = new HashMap<>();
    private final Map<String, Person> nameCache = new HashMap<>();
    private final Map<String, Person> addressCache = new HashMap<>();
    private final Map<String, Person> phoneNumberCache = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        idCache.put(person.id(), person);
        nameCache.put(person.name(), person);
        addressCache.put(person.address(), person);
        phoneNumberCache.put(person.phoneNumber(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idCache.remove(id);

        if (person != null) {
            nameCache.remove(person.name());
            addressCache.remove(person.address());
            phoneNumberCache.remove(person.phoneNumber());
        }
    }

    @Override
    public synchronized Person findByName(String name) {
        return nameCache.get(name);
    }

    @Override
    public synchronized Person findByAddress(String address) {
        return addressCache.get(address);
    }

    @Override
    public synchronized Person findByPhone(String phone) {
        return phoneNumberCache.get(phone);
    }
}
