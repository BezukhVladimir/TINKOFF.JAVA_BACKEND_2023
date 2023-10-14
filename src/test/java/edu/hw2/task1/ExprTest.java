package edu.hw2.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExprTest {
    @Test
    @DisplayName("Test Constant")
    void testConstant() {
        // Arrange
        Expr constant = new Constant(42.0);

        // Act
        double result = constant.evaluate();

        // Assert
        assertEquals(42.0, result);
    }

    @Test
    @DisplayName("Test Negate")
    void testNegate() {
        // Arrange
        Expr innerConstant = new Constant(10.0);
        Expr negate = new Negate(innerConstant);

        // Act
        double result = negate.evaluate();

        // Assert
        assertEquals(-10.0, result);
    }

    @Test
    @DisplayName("Test Exponent")
    void testExponent() {
        // Arrange
        Expr base = new Constant(2.0);
        Expr exponent = new Exponent(base, 3);

        // Act
        double result = exponent.evaluate();

        // Assert
        assertEquals(8.0, result);
    }

    @Test
    @DisplayName("Test Addition")
    void testAddition() {
        // Arrange
        Expr left = new Constant(5.0);
        Expr right = new Constant(7.0);
        Expr addition = new Addition(left, right);

        // Act
        double result = addition.evaluate();

        // Assert
        assertEquals(12.0, result);
    }

    @Test
    @DisplayName("Test Multiplication")
    void testMultiplication() {
        // Arrange
        Expr left = new Constant(3.0);
        Expr right = new Constant(4.0);
        Expr multiplication = new Multiplication(left, right);

        // Act
        double result = multiplication.evaluate();

        // Assert
        assertEquals(12.0, result);
    }
}
