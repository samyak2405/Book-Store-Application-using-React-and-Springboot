package com.javahunter.application.service;

public interface OtpService {
    public String generateOtp(String email);
    public boolean validateOtp(String email, String otp);
    public void removeOtp(String email);
}
