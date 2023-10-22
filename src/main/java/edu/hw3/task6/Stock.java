package edu.hw3.task6;

import java.util.Objects;

public record Stock(
    String name,
    double price
) implements Comparable<Stock> {
    @Override
    public int compareTo(Stock other) {
        return Double.compare(price, other.price);
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        return hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
