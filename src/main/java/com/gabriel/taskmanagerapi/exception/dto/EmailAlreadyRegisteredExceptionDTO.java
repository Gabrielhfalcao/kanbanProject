package com.gabriel.taskmanagerapi.exception.dto;

import java.time.LocalDateTime;

public record EmailAlreadyRegisteredExceptionDTO(
        LocalDateTime dateTime,
        String message
) {
    public EmailAlreadyRegisteredExceptionDTO(String message){
        this(
                LocalDateTime.now(),
                message);
    }
}
