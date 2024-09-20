package com.gabriel.taskmanagerapi.exception.dto;

import org.springframework.validation.FieldError;

public record ValidationErrorExceptionDTO(
        String field,
        String message
){
    public ValidationErrorExceptionDTO(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
