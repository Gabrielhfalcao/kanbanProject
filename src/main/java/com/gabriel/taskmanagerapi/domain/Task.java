package com.gabriel.taskmanagerapi.domain;

import com.gabriel.taskmanagerapi.enums.Priority;
import com.gabriel.taskmanagerapi.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime deadlineDate;
    private Priority priority;
    private Status status;
    @ManyToOne
    private User creator;
    @ManyToMany
    private List<User> responsible;
    @OneToMany
    private List<Comment> comments = new ArrayList<>();
    @OneToMany
    private List<Activity> activities = new ArrayList<>();
}
