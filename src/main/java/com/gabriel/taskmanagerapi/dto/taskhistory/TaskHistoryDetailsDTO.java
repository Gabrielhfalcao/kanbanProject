package com.gabriel.taskmanagerapi.dto.taskhistory;

import com.gabriel.taskmanagerapi.domain.TaskHistory;

public record TaskHistoryDetailsDTO(
        Long id,
        Long taskId,
        String taskTitle,
        String action
) {
    public TaskHistoryDetailsDTO(TaskHistory taskHistory){
        this(
                taskHistory.getId(),
                taskHistory.getTask().getId(),
                taskHistory.getTask().getTitle(),
                taskHistory.getAction()
        );
    }
}
