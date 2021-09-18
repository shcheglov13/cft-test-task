package ru.shcheglov.app.exceptions;

public class InvalidSortException extends Exception {
    public InvalidSortException(String errorMessage) {
        super(errorMessage);
    }
}
