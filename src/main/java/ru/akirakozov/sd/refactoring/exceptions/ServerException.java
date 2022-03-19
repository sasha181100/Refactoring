package ru.akirakozov.sd.refactoring.exceptions;

public class ServerException extends RuntimeException {
    public ServerException() {

    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}