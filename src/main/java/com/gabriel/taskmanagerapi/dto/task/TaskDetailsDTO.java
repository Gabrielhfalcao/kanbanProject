package com.gabriel.taskmanagerapi.dto.task;

import com.gabriel.taskmanagerapi.domain.Task;
import com.gabriel.taskmanagerapi.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public record TaskDetailsDTO (
        Long id,
        String title,
        String description,
        LocalDateTime deadlineDate,
        String priority,
        String status,
        String creator,
        List<String> responsible
){
    public TaskDetailsDTO(Task task){
        this(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDeadlineDate(),
                task.getPriority().toString(),
                task.getStatus().toString(),
                task.getCreator().getName(),
                task.getResponsible().stream().map(User::getName).toList()
        );
    }
}
