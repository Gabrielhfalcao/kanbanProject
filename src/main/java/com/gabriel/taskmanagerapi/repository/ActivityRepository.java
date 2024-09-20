package com.gabriel.taskmanagerapi.repository;

import com.gabriel.taskmanagerapi.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {}
