package com.gabriel.taskmanagerapi.dto.activity;

import com.gabriel.taskmanagerapi.domain.Activity;

public record ActivityDetailsDTO(
        Long id,
        String description,
        boolean checked
) {
    public ActivityDetailsDTO(Activity activity) {
        this(
                activity.getId(),
                activity.getDescription(),
                activity.isChecked());
    }
}
