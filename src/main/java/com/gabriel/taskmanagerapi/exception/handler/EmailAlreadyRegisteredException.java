package com.gabriel.taskmanagerapi.exception.handler;

public class EmailAlreadyRegisteredException extends RuntimeException{
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
