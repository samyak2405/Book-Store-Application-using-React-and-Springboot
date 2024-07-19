package com.javahunter.application.service;

import com.javahunter.application.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

    UserDto getUserById(Long userId);

    public UserDetailsService userDetailsService();
}
