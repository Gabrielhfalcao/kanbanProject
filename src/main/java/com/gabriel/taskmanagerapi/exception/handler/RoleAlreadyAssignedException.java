package com.gabriel.taskmanagerapi.exception.handler;

public class RoleAlreadyAssignedException extends RuntimeException {
    public RoleAlreadyAssignedException(String message) {
        super(message);
    }
}
