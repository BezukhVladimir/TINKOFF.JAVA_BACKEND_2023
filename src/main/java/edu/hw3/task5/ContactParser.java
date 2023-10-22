package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class ContactParser {
    private ContactParser() {
    }

    private final static String SPLIT_REGEX = " ";

    public enum ORDER {
        ASC, DESC
    }

    public static List<Contact> parseContacts(String[] fullNames, ORDER order) {
        if (fullNames == null) {
            return List.of();
        }

        if (fullNames.length == 0) {
            return List.of();
        }

        List<Contact> contacts = createContacts(fullNames);
        sortContacts(contacts, order);

        return contacts;
    }

    private static @NotNull List<Contact> createContacts(String[] fullNames) {
        List<Contact> contacts = new ArrayList<>();

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
