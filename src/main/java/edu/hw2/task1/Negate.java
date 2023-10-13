package edu.hw2.task1;

public record Negate(Expr inner) implements Expr {
    @Override
    public double evaluate() {
        return -inner.evaluate();
    }
}
