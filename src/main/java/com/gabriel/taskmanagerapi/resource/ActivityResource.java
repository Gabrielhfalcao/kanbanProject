package com.gabriel.taskmanagerapi.resource;

import com.gabriel.taskmanagerapi.dto.activity.ActivityDetailsDTO;
import com.gabriel.taskmanagerapi.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activities")
public class ActivityResource {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public ResponseEntity<Page<ActivityDetailsDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(activityService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDetailsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(activityService.findById(id));
    }
}
