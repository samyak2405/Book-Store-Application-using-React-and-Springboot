package com.javahunter.application.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class UserDto {

    private Integer id;

    @NotBlank(message = "Firstname is mandatory")
    @Size(max = 50, message = "Firstname should not be more than 50 characters")
    @Column(name = "first_name", nullable = false)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Firstname must contain only letters")
    private String firstName;

    @NotBlank(message = "Lastname is mandatory")
    @Size(max = 50, message = "Lastname should not be more than 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "LastName must contain only letters")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email should not be more than 100 characters")
    private String email;

    @NotBlank(message = "PhoneNumber is mandatory")
    @Size(max = 20, message = "PhoneNumber should not be more than 20 characters")
    private String phoneNumber;

    @NotBlank(message = "Username is mandatory")
    @Size(max = 50, message = "Username should not be more than 50 characters")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;

    private Boolean enabled;

    private RoleDTO role;

    private Set<AddressDTO> addresses;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

