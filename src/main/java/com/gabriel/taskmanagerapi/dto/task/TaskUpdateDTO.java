package com.gabriel.taskmanagerapi.dto.task;

import com.gabriel.taskmanagerapi.domain.Task;
import com.gabriel.taskmanagerapi.domain.User;
import com.gabriel.taskmanagerapi.enums.Priority;
import com.gabriel.taskmanagerapi.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public record TaskUpdateDTO(
        String title,
        String description,
        LocalDateTime deadlineDate,
        Priority priority,
        Status status,
        List<Long> responsibleId
) {
    public TaskUpdateDTO(Task task) {
        this(
                task.getTitle(),
                task.getDescription(),
                task.getDeadlineDate(),
                task.getPriority(),
                task.getStatus(),
                task.getResponsible().stream().map(User::getId).toList()
        );
    }
}
