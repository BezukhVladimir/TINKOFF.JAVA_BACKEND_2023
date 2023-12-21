package edu.hw02.task1;

public record Constant(double value) implements Expr {
    @Override
    public double evaluate() {
        return value;
    }
}
