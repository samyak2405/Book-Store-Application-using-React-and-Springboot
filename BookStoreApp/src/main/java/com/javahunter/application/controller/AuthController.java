package com.javahunter.application.controller;

import com.javahunter.application.config.ApiMessages;
import com.javahunter.application.dto.*;
import com.javahunter.application.entity.OtpDto;
import com.javahunter.application.service.AuthService;
import com.javahunter.application.service.OtpService;
import com.javahunter.application.service.TemporaryUserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

/**
 * AuthController is responsible for handling user authentication and
 * authorization operations. It provides endpoints for user registration,
 * sign-in, and password reset functionalities.
 *
 * <p>This controller interacts with the AuthService to perform the necessary
 * business logic and uses ApiMessages to fetch relevant messages for API
 * responses. Logging is incorporated to facilitate tracking and debugging
 * of operations.</p>
 *
 * <p>Endpoints:</p>
 * <ul>
 * <li><code>/api/v1/auth/register</code>: Registers a new user.</li>
 * <li><code>/api/v1/auth/sign-in</code>: Authenticates a user and returns an auth token.</li>
 * <li><code>/api/v1/auth/reset-password</code>: Resets the password for a user.</li>
 * </ul>
 *
 * <p>HTTP Status Codes:</p>
 * <ul>
 * <li><code>200 OK</code>: The request was successful.</li>
 * <li><code>400 BAD REQUEST</code>: The request was invalid or failed.</li>
 * <li><code>500 INTERNAL SERVER ERROR</code>: An error occurred on the server.</li>
 * </ul>
 *
 * <p>Dependencies:</p>
 * <ul>
 * <li>AuthService: Handles the core business logic for authentication operations.</li>
 * <li>ApiMessages: Provides messages for API responses.</li>
 * <li>UserDto, AuthRequest, AuthResponse, ResetPasswordRequest, ApiResponses: Data transfer objects used in API operations.</li>
 * </ul>
 *
 * @see AuthService
 * @see ApiMessages
 * @see UserDto
 * @see AuthRequest
 * @see AuthResponse
 * @see ResetPasswordRequest
 * @see ApiResponses
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final TemporaryUserService temporaryUserService;
    private final OtpService otpService;
    private final ApiMessages apiMessages;
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    /**
     * Registers a new user.
     *
     * @param userDto the user data transfer object containing user details
     * @return a response entity with a success message or an error message
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponses> registerUser(@RequestBody @Valid UserDto userDto){
        try{
            authService.registerUser(userDto);
        }catch (RoleNotFoundException e){
            log.info("Role Does not exists");
            return new ResponseEntity<>(ApiResponses.builder()
                    .success(false)
                    .message(apiMessages.getMessage("role.does.not.exists"))
                    .responseBody("User registration failure")
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ApiResponses.builder()
                .success(true)
                .message(apiMessages.getMessage("user.validate.otp"))
                .responseBody("Please verify the otp sent on your email")
                .build(), HttpStatus.CREATED);
    }

    @PostMapping("/validate/otp")
    public ResponseEntity<ApiResponses> validateOtp(@RequestBody @Valid OtpDto otpDto) {
        TemporaryUser tempUser = temporaryUserService.getTemporaryUser(otpDto.getEmail());

        if (tempUser != null && otpService.validateOtp(otpDto.getEmail(), otpDto.getOtp())) {
            authService.saveUser(tempUser.getUserDto());

            otpService.removeOtp(otpDto.getEmail());
            temporaryUserService.removeTemporaryUser(otpDto.getEmail());

            return ResponseEntity.ok(ApiResponses.builder()
                            .success(true)
                            .message(apiMessages.getMessage("user.registration.success"))
                            .responseBody("Otp Validation success, You can login now with email and password")
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponses.builder()
                            .success(false)
                            .message(apiMessages.getMessage("user.registration.failed"))
                            .responseBody("Otp Validation failed, kindly generate otp again and try again")
                    .build());
        }
    }

    /**
     * Authenticates a user and returns an authentication response containing the auth token.
     *
     * @param authRequest the authentication request containing username and password
     * @return a response entity with the authentication response or an error
     */
    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "getUserAttribute",
                            summary = "Retrieves a User's attributes.",
                            description = "Retrieves a User's attributes.",
                            value = "[{\"value\": [\"area1\", \"area2\", \"area3\"], \"key\":\"GENERAL_AREAS\"}, {\"value\":\"933933933\", \"key\":\"FONyE\"}]")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest){

        log.info("Inside sign-in controller");
        AuthResponse authResponse = authService.authenticateUser(authRequest);
        if (authResponse == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(authResponse);
    }

    /**
     * Resets the password for a user.
     *
     * @param passwordRequest the password reset request containing necessary information
     * @return a response entity with a success message or an error message
     */
    @PutMapping("/reset-password")
    public ResponseEntity<ApiResponses> resetPassword(@Valid @RequestBody ResetPasswordRequest passwordRequest){
        log.info("Reset Password Controller");
        boolean isPasswordChanged = authService.resetPassword(passwordRequest);
        if (isPasswordChanged)
            return new ResponseEntity<>(ApiResponses.builder()
                    .message(apiMessages.getMessage("success.auth.reset-password"))
                    .success(true)
                    .build(), HttpStatus.OK);
        return new ResponseEntity<>(ApiResponses.builder()
                .message(apiMessages.getMessage("error.auth.reset-password"))
                .success(false)
                .build(), HttpStatus.BAD_REQUEST);
    }
}
