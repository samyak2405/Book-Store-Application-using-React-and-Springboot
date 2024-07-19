package com.javahunter.application.service.impl;

import com.javahunter.application.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

        private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
        public String generateOtp(String email) {
            String otp = String.valueOf(new Random().nextInt(900000) + 100000);
            otpStorage.put(email, otp);
            return otp;
        }

        public boolean validateOtp(String email, String otp) {
            return otp.equals(otpStorage.get(email));
        }

        public void removeOtp(String email) {
            otpStorage.remove(email);
        }
    }
