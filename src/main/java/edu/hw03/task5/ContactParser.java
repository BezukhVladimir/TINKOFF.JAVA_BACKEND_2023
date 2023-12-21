package edu.hw03.task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class ContactParser {
    private ContactParser() {
    }

    private final static String SPLIT_REGEX = " ";

    public enum ORDER {
        ASC, DESC
    }

    public static List<Contact> parseContacts(String[] fullNames, ORDER order) {
        if (isArrayEmpty(fullNames)) {
            return List.of();
        }

        List<Contact> contacts = createContacts(fullNames);
        sortContacts(contacts, order);

        return contacts;
    }

    private static boolean isArrayEmpty(String[] array) {
        return array == null || array.length == 0;
    }

    private static List<Contact> createContacts(String[] fullNames) {
        List<Contact> contacts = new ArrayList<>(fullNames.length);

        for (String fullName : fullNames) {
            String[] data = fullName.split(SPLIT_REGEX);
            String firstName = data[0];
            String secondName = data.length == 2 ? data[1] : null;

            contacts.add(new Contact(firstName, secondName));
        }

        return contacts;
    }

    private static void sortContacts(List<Contact> contacts, ORDER order) {
        Comparator<Contact> comparator = switch (order) {
            case ASC  -> Comparator.naturalOrder();
            case DESC -> Comparator.reverseOrder();
        };

        contacts.sort(comparator);
    }
}
