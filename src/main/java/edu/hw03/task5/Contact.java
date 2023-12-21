package edu.hw03.task5;

import org.jetbrains.annotations.NotNull;

public record Contact(
    String firstName,
    String secondName
) implements Comparable<Contact> {
    @Override
    public int compareTo(@NotNull Contact other) {
        if (this.secondName == null || other.secondName == null) {
            return this.firstName.compareTo(other.firstName);
        }

        int secondNameComparison = this.secondName.compareTo(other.secondName);

        if (secondNameComparison == 0) {
            return this.firstName.compareTo(other.firstName);
        }

        return secondNameComparison;
    }
}
