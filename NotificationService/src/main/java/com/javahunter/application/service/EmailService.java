package com.javahunter.application.service;

import com.javahunter.application.dto.UserDto;

public interface EmailService {
    public void sendRegistrationEmail(UserDto user);

}
