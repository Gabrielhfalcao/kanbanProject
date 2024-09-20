package com.gabriel.taskmanagerapi.domain;

import com.gabriel.taskmanagerapi.dto.user.UserRegisterDTO;
import com.gabriel.taskmanagerapi.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
    @OneToMany
    private List<Task> createdTasks = new ArrayList<>();
    @OneToMany
    private List<Task> atributedTasks = new ArrayList<>();
    private Integer createdTasksCount = createdTasks.size();
    private Integer atributedTasksCount = atributedTasks.size();

    public User(UserRegisterDTO userRegisterDTO) {
        this.name = userRegisterDTO.name();
        this.email = userRegisterDTO.email();
        this.password = userRegisterDTO.password();
        this.userRole = userRegisterDTO.userRole();
    }
}
