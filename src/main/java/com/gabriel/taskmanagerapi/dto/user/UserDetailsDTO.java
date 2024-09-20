package com.gabriel.taskmanagerapi.dto.user;

import com.gabriel.taskmanagerapi.domain.User;

public record UserDetailsDTO (
        Long id,
        String name,
        String email,
        String userRole,
        Integer createdTasksCount,
        Integer atributedTasksCount
){
    public UserDetailsDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserRole().toString(),
                user.getCreatedTasksCount(),
                user.getAtributedTasksCount()
        );
    }
}
