package com.gabriel.taskmanagerapi.dto.activity;

import com.gabriel.taskmanagerapi.domain.Activity;
import jakarta.validation.constraints.NotBlank;

public record ActivityUpdateDTO(
        @NotBlank
        String description
) {
    public ActivityUpdateDTO(Activity activity) {
        this(
                activity.getDescription()
        );
    }
}
