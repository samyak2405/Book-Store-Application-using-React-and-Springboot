package com.javahunter.application.service.impl;

import com.javahunter.application.dto.KafkaDto;
import com.javahunter.application.dto.UserDto;
import com.javahunter.application.service.EmailService;
import com.javahunter.application.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final EmailService emailService;

    @Override
    @KafkaListener(topics = "user-registration", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleUserRegistration(UserDto userDto) {
        emailService.sendRegistrationEmail(userDto);
    }
}
