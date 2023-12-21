package edu.hw02.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ExprTest {
    @Test
    @DisplayName("Test Constant")
    void constant() {
        // Arrange
        Expr constant = new Constant(42.0);

        // Act
        double result = constant.evaluate();

        // Assert
        assertThat(result).isEqualTo(42.0);
    }

    @Test
    @DisplayName("Test Negate")
    void negate() {
        // Arrange
        Expr innerConstant = new Constant(10.0);
        Expr negate = new Negate(innerConstant);

        // Act
        double result = negate.evaluate();

        // Assert
        assertThat(result).isEqualTo(-10.0);

    }

    @Test
    @DisplayName("Test Exponent")
    void exponent() {
        // Arrange
        Expr base = new Constant(2.0);
        Expr exponent = new Exponent(base, 3);

        // Act
        double result = exponent.evaluate();

        // Assert
        assertThat(result).isEqualTo(8.0);
    }

    @Test
    @DisplayName("Test Addition")
    void addition() {
        // Arrange
        Expr left = new Constant(5.0);
        Expr right = new Constant(7.0);
        Expr addition = new Addition(left, right);

        // Act
        double result = addition.evaluate();

        // Assert
        assertThat(result).isEqualTo(12.0);
    }

    @Test
    @DisplayName("Test Multiplication")
    void multiplication() {
        // Arrange
        Expr left = new Constant(3.0);
        Expr right = new Constant(4.0);
        Expr multiplication = new Multiplication(left, right);

        // Act
        double result = multiplication.evaluate();

        // Assert
        assertThat(result).isEqualTo(12.0);
    }
}
