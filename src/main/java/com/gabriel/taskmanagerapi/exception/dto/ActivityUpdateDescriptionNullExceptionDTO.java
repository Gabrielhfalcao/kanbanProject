package com.gabriel.taskmanagerapi.exception.dto;

import java.time.LocalDateTime;

public record ActivityUpdateDescriptionNullExceptionDTO(
        LocalDateTime timeStamp,
        String message
) {
    public ActivityUpdateDescriptionNullExceptionDTO (String message){
        this(
                LocalDateTime.now(),
                message
        );
    }
}
