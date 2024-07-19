package com.javahunter.application.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpDto {

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email should not be more than 100 characters")
        private String email;

        @NotBlank(message = "OTP is mandatory")
        @Size(min = 6, max = 6, message = "OTP should be exactly 6 characters long")
        @Pattern(regexp = "^[0-9]{6}$", message = "OTP must be a 6-digit number")
        private String otp;
}

