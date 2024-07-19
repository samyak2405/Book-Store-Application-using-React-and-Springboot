package com.javahunter.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class AuthRequest {

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
