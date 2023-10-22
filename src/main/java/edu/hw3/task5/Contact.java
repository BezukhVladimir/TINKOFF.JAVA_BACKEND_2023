package edu.hw3.task5;

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

        return this.secondName.compareTo(other.secondName);
    }
}
