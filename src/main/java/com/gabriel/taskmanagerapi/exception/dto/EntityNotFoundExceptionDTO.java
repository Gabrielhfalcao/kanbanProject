package com.gabriel.taskmanagerapi.exception.dto;

import java.time.LocalDateTime;

public record EntityNotFoundExceptionDTO(
        LocalDateTime dateTime,
        String message
){
    public EntityNotFoundExceptionDTO(String message) {
        this(
                LocalDateTime.now(),
                message
        );
    }
}
