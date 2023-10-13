package edu.hw2.task2;

public class Rectangle {
    private final double width;
    private final double height;

    public Rectangle() {
        this(0.0, 0.0);
    }

    /**
     * Constructs a new rectangle with the specified width and height.
     *
     * @param width  The width of the rectangle. Must be a positive value.
     * @param height The height of the rectangle. Must be a positive value.
     * @throws IllegalArgumentException If width or height is not a positive value.
     */
    public Rectangle(double width, double height) {
        if (width < 0.0 || height < 0.0) {
            throw new IllegalArgumentException("Width and height must be positive values.");
        }

        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new rectangle by changing its width while keeping the original height.
     *
     * @param width The new width for the rectangle. Must be a positive value.
     * @return A new rectangle with the specified width and the original height.
     * @throws IllegalArgumentException If the width is not a positive value.
     */
    public final Rectangle createWithWidth(double width) {
        return new Rectangle(width, this.height);
    }

    /**
     * Creates a new rectangle by changing its height while keeping the original width.
     *
     * @param height The new height for the rectangle. Must be a positive value.
     * @return A new rectangle with the specified height and the original width.
     * @throws IllegalArgumentException If the height is not a positive value.
     */
    public final Rectangle createWithHeight(double height) {
        return new Rectangle(this.width, height);
    }

    public final double getWidth() {
        return width;
    }

    public final double getHeight() {
        return height;
    }

    public double area() {
        return width * height;
    }
}
