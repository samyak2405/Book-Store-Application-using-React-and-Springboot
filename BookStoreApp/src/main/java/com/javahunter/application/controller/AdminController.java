package com.javahunter.application.controller;

import com.javahunter.application.dto.UserDto;
import com.javahunter.application.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AdminController is responsible for handling administrative operations
 * such as managing user accounts within the application. It provides
 * endpoints for retrieving all users, enabling or disabling user accounts,
 * and fetching user details by their ID.
 *
 * <p>This controller makes use of the AdminService to perform the necessary
 * business logic and interacts with the UserDto to transfer user data
 * across different layers of the application. Logging is incorporated to
 * facilitate tracking and debugging of operations.</p>
 *
 * <p>Endpoints:</p>
 * <ul>
 * <li><code>/api/v1/admin/hello</code>: A simple endpoint to test the controller.</li>
 * <li><code>/api/v1/admin/users</code>: Retrieves a list of all users.</li>
 * <li><code>/api/v1/admin/users/disable/{userId}</code>: Disables a specific user account by ID.</li>
 * <li><code>/api/v1/admin/users/enable/{userId}</code>: Enables a specific user account by ID.</li>
 * <li><code>/api/v1/admin/users/{userId}</code>: Retrieves details of a specific user by ID.</li>
 * </ul>
 *
 * <p>HTTP Status Codes:</p>
 * <ul>
 * <li><code>200 OK</code>: The request was successful.</li>
 * <li><code>204 NO CONTENT</code>: No users found in the system.</li>
 * <li><code>404 NOT FOUND</code>: The requested user was not found.</li>
 * </ul>
 *
 * <p>Dependencies:</p>
 * <ul>
 * <li>AdminService: Handles the core business logic for admin operations.</li>
 * <li>UserDto: Data transfer object representing user information.</li>
 * </ul>
 *
 * @see AdminService
 * @see UserDto
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@Tag(
        name = "AdminController",
        description = "Admin Controller is responsible for performing operations related to Admins."
)
public class AdminController {
    private final AdminService adminService;
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    /**
     * A simple endpoint to test the AdminController.
     *
     * @return a greeting message from the admin controller
     */
    @GetMapping("/hello")
    public String hello() {
        log.info("Inside Hello test Admin Controller Method");
        return "Hello From admin";
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a response entity with the list of all users or a message indicating no users found
     */
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> userDtos = adminService.getAllUsers();
        if (userDtos.isEmpty())
            return new ResponseEntity<>("No user found", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    /**
     * Disables a specific user account by ID.
     *
     * @param userId the ID of the user to disable
     * @return a response entity indicating the result of the operation
     */
    @PutMapping("/users/disable/{userId}")
    public ResponseEntity<String> disableUser(@PathVariable Long userId) {
        boolean isDisabled = adminService.disableUser(userId);
        if (isDisabled)
            return new ResponseEntity<>("User Disabled Successfully", HttpStatus.OK);
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    /**
     * Enables a specific user account by ID.
     *
     * @param userId the ID of the user to enable
     * @return a response entity indicating the result of the operation
     */
    @PutMapping("/users/enable/{userId}")
    public ResponseEntity<String> enableUser(@PathVariable Long userId) {
        boolean isEnabled = adminService.enableUser(userId);
        if (isEnabled)
            return new ResponseEntity<>("User Enabled Successfully", HttpStatus.OK);
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    /**
     * Retrieves details of a specific user by ID.
     *
     * @param userId the ID of the user to retrieve
     * @return a response entity with the user details or a message indicating the user was not found
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        UserDto userDto = adminService.getUserById(userId);
        if (userDto == null) {
            return new ResponseEntity<>("User not found with id " + userId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
