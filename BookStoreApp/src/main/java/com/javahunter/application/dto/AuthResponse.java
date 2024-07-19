package com.javahunter.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String username;
    private String token;
    private String refreshToken;
}

