package ru.akirakozov.sd.refactoring.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException() {

    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
    
}