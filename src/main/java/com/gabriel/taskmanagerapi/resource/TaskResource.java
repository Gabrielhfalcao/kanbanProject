package com.gabriel.taskmanagerapi.resource;

import com.gabriel.taskmanagerapi.dto.task.TaskDetailsDTO;
import com.gabriel.taskmanagerapi.dto.task.TaskRegisterDTO;
import com.gabriel.taskmanagerapi.dto.task.TaskUpdateDTO;
import com.gabriel.taskmanagerapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tasks")
public class TaskResource {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskDetailsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new TaskDetailsDTO(taskService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<TaskDetailsDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(taskService.findAll(pageable));
    }

    @PostMapping("/register")
    public ResponseEntity<TaskDetailsDTO> register(@Valid @RequestBody TaskRegisterDTO taskRegisterDTO, UriComponentsBuilder uriComponentsBuilder) {
        TaskDetailsDTO taskDetailsDTO = taskService.register(taskRegisterDTO);
        var uri = UriComponentsBuilder.fromPath("/tasks/{id}").buildAndExpand(taskDetailsDTO).toUri();
        return ResponseEntity.created(uri).body(taskDetailsDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDetailsDTO> update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO taskUpdateDTO) {
        return ResponseEntity.ok().body(taskService.update(id, taskUpdateDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
