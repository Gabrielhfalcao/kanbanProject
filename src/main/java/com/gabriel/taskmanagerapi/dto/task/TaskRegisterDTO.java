package com.gabriel.taskmanagerapi.dto.task;

import com.gabriel.taskmanagerapi.domain.User;
import com.gabriel.taskmanagerapi.enums.Priority;
import com.gabriel.taskmanagerapi.enums.Status;
import com.gabriel.taskmanagerapi.domain.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record TaskRegisterDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        LocalDateTime deadlineDate,
        @NotNull
        Priority priority,
        @NotNull
        Status status,
        @NotNull
        Long creatorId,
        @NotNull
        List<Long> responsibleId) {
    public TaskRegisterDTO(Task task){
        this(
                task.getTitle(),
                task.getDescription(),
                task.getDeadlineDate(),
                task.getPriority(),
                task.getStatus(),
                task.getCreator().getId(),
                task.getResponsible().stream().map(User::getId).toList());
    }
}
