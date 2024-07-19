package com.javahunter.application.service;

import com.javahunter.application.dto.AdminDto;
import com.javahunter.application.dto.UserDto;

import java.util.List;

public interface AdminService {
    AdminDto createAdmin(AdminDto adminDto);
    AdminDto updateAdmin(Long adminId, AdminDto adminDto);
    void deleteAdmin(Long adminId);
    AdminDto getAdminById(Long adminId);
    List<AdminDto> getAllAdmins();
    public List<UserDto> getAllUsers();

    boolean disableUser(Long userId);

    public boolean enableUser(Long userId);

    UserDto getUserById(Long userId);
}
