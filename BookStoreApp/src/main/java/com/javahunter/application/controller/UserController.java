package com.javahunter.application.controller;

import com.javahunter.application.dto.AuthRequest;
import com.javahunter.application.dto.AuthResponse;
import com.javahunter.application.dto.UserDto;
import com.javahunter.application.service.AuthService;
import com.javahunter.application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UserController is responsible for handling user-related operations such as
 * retrieving user details. It interacts with the UserService and AuthService
 * to perform the necessary business logic and uses UserDto to transfer user
 * data across different layers of the application.
 *
 * <p>This controller provides endpoints for the following operations:</p>
 * <ul>
 * <li><code>/api/v1/user/hello</code>: A simple endpoint to test the controller.</li>
 * <li><code>/api/v1/user/users/{userId}</code>: Retrieves a user's details by their ID.</li>
 * </ul>
 *
 * <p>HTTP Status Codes:</p>
 * <ul>
 * <li><code>200 OK</code>: The request was successful.</li>
 * <li><code>404 NOT FOUND</code>: The requested resource was not found.</li>
 * </ul>
 *
 * <p>Dependencies:</p>
 * <ul>
 * <li>UserService: Handles the core business logic for user operations.</li>
 * <li>AuthService: Handles authentication and authorization logic.</li>
 * <li>UserDto: Data transfer object representing user information.</li>
 * </ul>
 *
 * @see UserService
 * @see AuthService
 * @see UserDto
 */

@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    /**
     * A simple endpoint to test the UserController.
     *
     * @return a greeting message from the user controller
     */
    @GetMapping("/hello")
    public String hello() {
        log.info("Inside Hello test User Controller Method");
        return "Hello From user";
    }

    /**
     * Retrieves a user's details by their ID.
     *
     * @param userId the ID of the user
     * @return a response entity with the user details or an error message
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        UserDto userDto = userService.getUserById(userId);
        if (userDto == null) {
            return new ResponseEntity<>("User not found with id " + userId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
