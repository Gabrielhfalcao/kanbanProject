package com.gabriel.taskmanagerapi.exception.dto;

import java.time.LocalDateTime;

public record RoleAlreadyAssignedExceptionDTO(
        LocalDateTime timeStamp,
        String message
) {
    public RoleAlreadyAssignedExceptionDTO(String message) {
        this(
                LocalDateTime.now(),
                message
        );
    }
}
