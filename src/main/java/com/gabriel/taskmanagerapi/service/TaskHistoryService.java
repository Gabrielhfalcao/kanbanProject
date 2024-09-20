package com.gabriel.taskmanagerapi.service;

import com.gabriel.taskmanagerapi.domain.TaskHistory;
import com.gabriel.taskmanagerapi.dto.taskhistory.TaskHistoryDetailsDTO;
import com.gabriel.taskmanagerapi.dto.taskhistory.TaskHistoryRegisterDTO;
import com.gabriel.taskmanagerapi.repository.TaskHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskHistoryService {

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

    @Autowired
    private TaskService taskService;

    private static final String PREFIX = "There are no taskHistories for this id: ";

    public TaskHistoryDetailsDTO findById(Long id) {
        if(!taskHistoryRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        return new TaskHistoryDetailsDTO(taskHistoryRepository.getReferenceById(id));
    }

    public Page<TaskHistoryDetailsDTO> findAll(Pageable pageable) {
        return taskHistoryRepository.findAll(pageable).map(TaskHistoryDetailsDTO::new);

    }

    public TaskHistoryDetailsDTO save(TaskHistoryRegisterDTO taskHistoryRegisterDTO) {
        return new TaskHistoryDetailsDTO(
                taskHistoryRepository
                        .save(convertTaskHistoryRegisterDTOToTaskHistory(taskHistoryRegisterDTO))
        );
    }

    public void deleteById(Long id) {
        if(!taskHistoryRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        taskHistoryRepository.deleteById(id);
    }

    public TaskHistory convertTaskHistoryRegisterDTOToTaskHistory(TaskHistoryRegisterDTO taskHistoryRegisterDTO) {
        TaskHistory taskHistory = new TaskHistory();
        taskHistory.setTask(taskService.findById(taskHistoryRegisterDTO.taskId()));
        taskHistory.setAction(taskHistoryRegisterDTO.action());
        return taskHistory;
    }
}
