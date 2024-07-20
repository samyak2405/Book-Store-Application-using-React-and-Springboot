package com.javahunter.application.service.impl;

import com.javahunter.application.dto.UserDto;
import com.javahunter.application.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;
    @Override
    public void sendRegistrationEmail(UserDto user) {
        Context context = new Context();
        context.setVariable("firstName", user.getFirstName());
        context.setVariable("lastName", user.getLastName());
        context.setVariable("userName", user.getUserName());
        context.setVariable("email", user.getEmail());
        context.setVariable("phoneNumber", user.getPhoneNumber());
        context.setVariable("role", user.getRole().getName());
        context.setVariable("applicationName", "Sam Book Store");

        String emailContent = templateEngine.process("registration-success", context);

        sendHtmlMessage(user.getEmail(), "Registration Successful", emailContent);
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailSendException("Failed to send email", e);
        }
    }
}
