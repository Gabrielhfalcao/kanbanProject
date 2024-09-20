package com.gabriel.taskmanagerapi.dto.taskhistory;

import jakarta.validation.constraints.NotNull;

public record TaskHistoryRegisterDTO(
        @NotNull
        Long taskId,
        @NotNull
        String action
) {
}
