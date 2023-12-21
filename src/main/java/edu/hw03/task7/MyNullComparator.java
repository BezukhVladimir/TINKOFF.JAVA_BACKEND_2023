package edu.hw03.task7;

import java.util.Comparator;

public class MyNullComparator<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T lhs, T rhs) {
        if (lhs == null && rhs == null) {
            return 0;
        }

        if (lhs == null) {
            return -1;
        }

        if (rhs == null) {
            return 1;
        }

        return lhs.compareTo(rhs);
    }
}
