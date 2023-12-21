package edu.hw03.task5;

import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw03.task5.ContactParser.parseContacts;
import static org.assertj.core.api.Assertions.assertThat;

class ContactParserTest {
    @Test
    void emptyInAscendingOrder() {
        // Arrange
        String[] fullNames = {};
        var order = ContactParser.ORDER.ASC;
        List<Contact> expected = List.of();

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertThat(contacts).isEqualTo(expected);
    }

    @Test
    void emptyInDescendingOrder() {
        // Arrange
        String[] fullNames = {};
        var order = ContactParser.ORDER.DESC;
        List<Contact> expected = List.of();

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertThat(contacts).isEqualTo(expected);
    }

    @Test
    void nullInAscendingOrder() {
        // Arrange
        String[] fullNames = null;
        var order = ContactParser.ORDER.ASC;
        List<Contact> expected = List.of();

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertThat(contacts).isEqualTo(expected);
    }

    @Test
    void nullInDescendingOrder() {
        // Arrange
        String[] fullNames = null;
        var order = ContactParser.ORDER.DESC;
        List<Contact> expected = List.of();

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertThat(contacts).isEqualTo(expected);
    }

    @Test
    void fullNamesInAscendingOrder() {
        // Arrange
        String[] fullNames = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        var order = ContactParser.ORDER.ASC;
        List<Contact> expected = List.of(
            new Contact("Paul", "Erdos"),
            new Contact("Leonhard", "Euler"),
            new Contact("Carl", "Gauss")
        );

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertThat(contacts).isEqualTo(expected);
    }

    @Test
    void fullNamesInDescendingOrder() {
        // Arrange
        String[] fullNames = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        var order = ContactParser.ORDER.DESC;
        List<Contact> expected = List.of(
            new Contact("Carl", "Gauss"),
            new Contact("Leonhard", "Euler"),
            new Contact("Paul", "Erdos")
        );

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertThat(contacts).isEqualTo(expected);
    }

    @Test
    void firstNamesInAscendingOrder() {
        // Arrange
        String[] fullNames = {"Paul", "Leonhard", "Carl"};
        var order = ContactParser.ORDER.ASC;
        List<Contact> expected = List.of(
            new Contact("Carl", null),
            new Contact("Leonhard", null),
            new Contact("Paul", null)
        );

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertThat(contacts).isEqualTo(expected);
    }

    @Test
    void firstNamesInDescendingOrder() {
        // Arrange
        String[] fullNames = {"Paul", "Leonhard", "Carl"};
        var order = ContactParser.ORDER.DESC;
        List<Contact> expected = List.of(
            new Contact("Paul", null),
            new Contact("Leonhard", null),
            new Contact("Carl", null)
        );

        // Act
        List<Contact> contacts = parseContacts(fullNames, order);

        // Assert
        assertThat(contacts).isEqualTo(expected);
    }
}
