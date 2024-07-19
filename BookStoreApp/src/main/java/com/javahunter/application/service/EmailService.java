package com.javahunter.application.service;

public interface EmailService {
    public void sendOtpEmail(String to, String userName, String otp, String applicationName);

}
