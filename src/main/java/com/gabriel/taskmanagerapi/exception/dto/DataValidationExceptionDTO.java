package com.gabriel.taskmanagerapi.exception.dto;

import java.time.LocalDateTime;

public record DataValidationExceptionDTO(
        LocalDateTime timeStamp,
        String error){
    public DataValidationExceptionDTO(String error, LocalDateTime timeStamp){
        this(
                timeStamp,
                error
        );
    }
}