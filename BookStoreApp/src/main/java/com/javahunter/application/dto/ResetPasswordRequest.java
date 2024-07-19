package com.javahunter.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordRequest {
    private String userName;
    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
