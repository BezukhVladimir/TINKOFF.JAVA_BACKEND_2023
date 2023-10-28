package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final ListIterator<T> listIterator;

    public BackwardIterator(Collection<T> collection) {
        if (collection instanceof List<T> list) {
            this.listIterator = list.listIterator(collection.size());
        } else {
            throw new IllegalArgumentException("BackwardIterator only supports List collections.");
        }
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasPrevious();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return listIterator.previous();
    }

    @Override
    public void remove() {
        listIterator.remove();
    }
}
