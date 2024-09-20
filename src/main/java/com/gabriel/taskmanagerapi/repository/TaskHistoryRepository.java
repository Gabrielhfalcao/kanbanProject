package com.gabriel.taskmanagerapi.repository;

import com.gabriel.taskmanagerapi.domain.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {}
