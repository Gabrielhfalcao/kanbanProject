package com.gabriel.taskmanagerapi.service;

import com.gabriel.taskmanagerapi.domain.Task;
import com.gabriel.taskmanagerapi.dto.task.TaskDetailsDTO;
import com.gabriel.taskmanagerapi.dto.task.TaskRegisterDTO;
import com.gabriel.taskmanagerapi.dto.task.TaskUpdateDTO;
import com.gabriel.taskmanagerapi.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {
    @Autowired
    private UserService userService;

    @Autowired
    private TaskRepository taskRepository;

    private static final String PREFIX = "There are no tasks for this id: ";

    public Page<TaskDetailsDTO> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable).map(TaskDetailsDTO::new);
    }

    public Task findById(Long id) {
        if(!taskRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        return taskRepository.getReferenceById(id);
    }

    public TaskDetailsDTO register(TaskRegisterDTO registerDTO) {
        return new TaskDetailsDTO(taskRepository.save(convertTaskRegisterDTOToTask(registerDTO)));
    }

    public TaskDetailsDTO update(Long id, TaskUpdateDTO taskUpdateDTO) {
        if(!taskRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        Task task = taskRepository.getReferenceById(id);
        task.setTitle(taskUpdateDTO.title());
        task.setDescription(taskUpdateDTO.description());
        task.setDeadlineDate(taskUpdateDTO.deadlineDate());
        task.setPriority(taskUpdateDTO.priority());
        task.setStatus(taskUpdateDTO.status());
        task.setResponsible(userService
                .convertLongListToUserList(taskUpdateDTO.responsibleId()));
        return new TaskDetailsDTO(taskRepository.save(task));
    }

    public void delete(Long id) {
        if(!taskRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        taskRepository.deleteById(id);
    }

    public Task convertTaskRegisterDTOToTask(TaskRegisterDTO taskRegisterDTO) {
        Task task = new Task();
        task.setTitle(taskRegisterDTO.title());
        task.setDescription(taskRegisterDTO.description());
        task.setCreationDate(LocalDateTime.now());
        task.setDeadlineDate(taskRegisterDTO.deadlineDate());
        task.setPriority(taskRegisterDTO.priority());
        task.setStatus(taskRegisterDTO.status());
        task.setCreator(userService.findById(taskRegisterDTO.creatorId()));
        task.setResponsible(userService
                .convertLongListToUserList(taskRegisterDTO.responsibleId()));

        return task;
    }
}
