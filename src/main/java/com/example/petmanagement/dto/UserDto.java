package com.example.petmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Role cannot be blank")
    private String role;

    @NotNull(message = "Unlocked status cannot be null")
    private boolean unlocked;
}
