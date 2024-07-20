package com.javahunter.application.service;

import com.javahunter.application.dto.UserDto;

public interface NotificationService {

    public void handleUserRegistration(UserDto userDto);
}
