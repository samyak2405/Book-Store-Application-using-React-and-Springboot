package com.javahunter.application.service;

import com.javahunter.application.dto.*;

import javax.management.relation.RoleNotFoundException;

public interface AuthService {
    AuthResponse authenticateUser(AuthRequest authRequest);
    void registerUser(UserDto userDto) throws RoleNotFoundException;
    public AuthResponse refreshToken(String token);

    boolean resetPassword(ResetPasswordRequest passwordRequest);

    void saveUser(UserDto userDto);
}
