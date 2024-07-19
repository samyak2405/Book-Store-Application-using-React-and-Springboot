package com.javahunter.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class AdminDto {

    private Integer id;

    @Schema(description = "Username will be used for login",example = "samyak2405")
    @NotEmpty(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Schema(description = "Password used for login",example = "Samyak@24")
    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private boolean enabled;
}
