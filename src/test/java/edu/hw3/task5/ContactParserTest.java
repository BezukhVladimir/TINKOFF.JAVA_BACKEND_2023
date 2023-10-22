package edu.hw3.task5;

import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw3.task5.ContactParser.parseContacts;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactParserTest {
    @Test
    void testEmpty() {
        // Arrange
        String[] fullNames = {};
        var order1 = ContactParser.ORDER.ASC;
        var order2 = ContactParser.ORDER.DESC;
        List<Contact> expected = List.of();

        // Act
        List<Contact> contacts1 = parseContacts(fullNames, order1);
        List<Contact> contacts2 = parseContacts(fullNames, order2);

        // Assert
        assertEquals(expected, contacts1);
        assertEquals(expected, contacts2);
    }

    @Test
    void testNull() {
        // Arrange
        String[] fullNames = null;
        var order1 = ContactParser.ORDER.ASC;
        var order2 = ContactParser.ORDER.DESC;
        List<Contact> expected = List.of();

        // Act
        List<Contact> contacts1 = parseContacts(fullNames, order1);
        List<Contact> contacts2 = parseContacts(fullNames, order2);

        // Assert
        assertEquals(expected, contacts1);
        assertEquals(expected, contacts2);
    }

    @Test
    void testFullNamesInAscendingOrder() {
        // Arrange
        String[] fullNames = { "Paul Erdos", "Leonhard Euler", "Carl Gauss" };
        var order = ContactParser.ORDER.ASC;
        List<Contact> expected = List.of(
            new Contact("Paul", "Erdos"),
            new Contact("Leonhard", "Euler"),
            new Contact("Carl", "Gauss")
        );

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertEquals(expected, contacts);
    }

    @Test
    void testFullNamesInDescendingOrder() {
        // Arrange
        String[] fullNames = { "Paul Erdos", "Leonhard Euler", "Carl Gauss" };
        var order = ContactParser.ORDER.DESC;
        List<Contact> expected = List.of(
            new Contact("Carl", "Gauss"),
            new Contact("Leonhard", "Euler"),
            new Contact("Paul", "Erdos")
        );

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertEquals(expected, contacts);
    }

    @Test
    void testFirstNamesInAscendingOrder() {
        // Arrange
        String[] fullNames = { "Paul", "Leonhard", "Carl" };
        var order = ContactParser.ORDER.ASC;
        List<Contact> expected = List.of(
            new Contact("Carl", null),
            new Contact("Leonhard", null),
            new Contact("Paul", null)
        );

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertEquals(expected, contacts);
    }

    @Test
    void testFirstNamesInDescendingOrder() {
        // Arrange
        String[] fullNames = { "Paul", "Leonhard", "Carl" };
        var order = ContactParser.ORDER.DESC;
        List<Contact> expected = List.of(
            new Contact("Paul", null),
            new Contact("Leonhard", null),
            new Contact("Carl", null)
        );

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertEquals(expected, contacts);
    }
}
