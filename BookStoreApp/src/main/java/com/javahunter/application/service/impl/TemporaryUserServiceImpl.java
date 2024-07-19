package com.javahunter.application.service.impl;

import com.javahunter.application.dto.TemporaryUser;
import com.javahunter.application.dto.UserDto;
import com.javahunter.application.service.TemporaryUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@RequiredArgsConstructor
public class TemporaryUserServiceImpl implements TemporaryUserService {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    private final Map<String, TemporaryUser> temporaryUsers = new ConcurrentHashMap<>();


    @Override
    public void storeTemporaryUser(UserDto userDto, String otp) {
        TemporaryUser tempUser = new TemporaryUser(userDto, otp);
        temporaryUsers.put(userDto.getEmail(), tempUser);
    }

    @Override
    public TemporaryUser getTemporaryUser(String email) {
        return temporaryUsers.get(email);
    }
    @Override
    public void removeTemporaryUser(String email) {
        temporaryUsers.remove(email);
    }
}
