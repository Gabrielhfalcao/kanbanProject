package com.gabriel.taskmanagerapi.repository;

import com.gabriel.taskmanagerapi.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
