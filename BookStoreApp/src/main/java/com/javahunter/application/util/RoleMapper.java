package com.javahunter.application.util;

import com.javahunter.application.dto.RoleDTO;
import com.javahunter.application.entity.Role;

public class RoleMapper {
    public static RoleDTO toDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static Role toEntity(RoleDTO roleDTO) {
        Role role = Role.builder()
                .name(roleDTO.getName())
                .build();
        role.setId(roleDTO.getId());
        return role;
    }
}
