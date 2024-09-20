package com.gabriel.taskmanagerapi.dto.user;

import com.gabriel.taskmanagerapi.domain.User;

public record UserUpdateDTO(
        String name,
        String email) {
    public UserUpdateDTO(User user) {
        this(
                user.getName(),
                user.getEmail()
        );
    }
}
