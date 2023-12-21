package edu.hw02.task1;

public record Addition(Expr left, Expr right) implements Expr {
    @Override
    public double evaluate() {
        return left.evaluate() + right.evaluate();
    }
}
