package com.javahunter.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {

    private Integer id;

    @NotBlank(message = "Role name is mandatory")
    @Size(max = 50, message = "Role name should not be more than 50 characters")
    private String name;
}
