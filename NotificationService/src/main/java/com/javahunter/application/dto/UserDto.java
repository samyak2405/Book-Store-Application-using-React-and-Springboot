package com.javahunter.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String userName;

    private String password;

    private Boolean enabled;

    private RoleDTO role;

    private Set<AddressDTO> addresses;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

