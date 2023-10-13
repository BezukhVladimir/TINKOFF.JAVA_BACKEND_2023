package edu.hw2.task2;

public class Square extends Rectangle {
    public Square() {
    }

    /**
     * Constructs a new square with the specified size.
     *
     * @param size The size of the square. Must be a positive value.
     * @throws IllegalArgumentException If size is not a positive value.
     */
    public Square(double size) {
        super(size, size);
    }

    /**
     * Creates a new square by changing its size.
     *
     * @param size The new size for the square. Must be a positive value.
     * @return A new square with the specified size.
     * @throws IllegalArgumentException If the size is not a positive value.
     */
    public final Square createWithSize(double size) {
        return new Square(size);
    }
}
