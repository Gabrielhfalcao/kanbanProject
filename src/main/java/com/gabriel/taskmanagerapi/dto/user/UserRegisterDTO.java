package com.gabriel.taskmanagerapi.dto.user;

import com.gabriel.taskmanagerapi.domain.User;
import com.gabriel.taskmanagerapi.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDTO(
        @NotBlank
        String name,
        @NotBlank @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        UserRole userRole) {
    public UserRegisterDTO(User user) {
        this(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserRole());
    }
}
