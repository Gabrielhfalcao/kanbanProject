package com.gabriel.taskmanagerapi.resource;

import com.gabriel.taskmanagerapi.domain.TaskHistory;
import com.gabriel.taskmanagerapi.dto.taskhistory.TaskHistoryDetailsDTO;
import com.gabriel.taskmanagerapi.service.TaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskHistories")
public class TaskHistoryResource {
    @Autowired
    private TaskHistoryService taskHistoryService;

    @GetMapping
    public ResponseEntity<Page<TaskHistoryDetailsDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(taskHistoryService.findAll(pageable));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<TaskHistoryDetailsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(taskHistoryService.findById(id));
    }

}
