package edu.project2.mazeengine.models;

public record Maze(
    Size size,
    Cell[][] grid
) {
    public static final class Size {
        private int height;
        private int width;

        public Size(int height, int width) {
            checkHeight(height);
            checkWidth(width);

            this.height = height;
            this.width = width;
        }

        public void setHeight(int height) {
            checkHeight(height);
            this.height = height;
        }

        public void setWidth(int width) {
            checkWidth(width);
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        private static void checkWidth(int width) {
            if (width < 0) {
                throw new IllegalArgumentException("Width cannot be negative.");
            }
        }

        private static void checkHeight(int height) {
            if (height < 0) {
                throw new IllegalArgumentException("Height cannot be negative.");
            }
        }
    }
}
