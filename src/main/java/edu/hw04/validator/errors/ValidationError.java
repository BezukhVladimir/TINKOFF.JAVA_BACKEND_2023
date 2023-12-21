package edu.hw04.validator.errors;

public class ValidationError extends Throwable {
    public ValidationError(String message) {
        super(message);
    }
}
