package com.gabriel.taskmanagerapi.dto.activity;

import com.gabriel.taskmanagerapi.domain.Activity;
import jakarta.validation.constraints.NotBlank;

public record ActivityRegisterDTO(
        @NotBlank
        String description
) {
    public ActivityRegisterDTO(Activity activity) {
        this(
                activity.getDescription()
        );
    }
}
