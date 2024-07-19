package com.javahunter.application.service;

import com.javahunter.application.dto.TemporaryUser;
import com.javahunter.application.dto.UserDto;

public interface TemporaryUserService {
    public void storeTemporaryUser(UserDto userDto, String otp);
    public TemporaryUser getTemporaryUser(String email);
    public void removeTemporaryUser(String email);
}
