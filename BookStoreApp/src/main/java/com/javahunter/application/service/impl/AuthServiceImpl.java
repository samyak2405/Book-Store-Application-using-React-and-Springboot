package com.javahunter.application.service.impl;

import com.javahunter.application.config.ApiMessages;
import com.javahunter.application.dto.*;
import com.javahunter.application.entity.Role;
import com.javahunter.application.entity.User;
import com.javahunter.application.exception.impl.UserNotEnabledException;
import com.javahunter.application.repository.RoleRepository;
import com.javahunter.application.repository.UserRepository;
import com.javahunter.application.service.*;
import com.javahunter.application.util.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final ApiMessages apiMessages;
    private final OtpService otpService;
    private final EmailService emailService;
    private final TemporaryUserService temporaryUserService;
    @Override
    public AuthResponse authenticateUser(AuthRequest authRequest) {
        log.info("Inside Authenticate User Service Method");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        User user = userRepository.findByUserName(authRequest.getUsername()).orElseThrow(()->new IllegalArgumentException("Invalid Username or password"));
        log.info("User enabled: {}",user.isEnabled());
        log.info("user: {}",user);
        if(!user.getEnabled()){
            log.info("User not enabled");
            throw new UserNotEnabledException(String.format(apiMessages.getMessage("user.not.enabled"),user.getUsername()));
        }
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        return AuthResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken)
                .username(authRequest.getUsername())
                .build();
    }

    @Override
    public void registerUser(UserDto userDto) throws RoleNotFoundException {
        log.info("Inside Sign up request");
        Role role = roleRepository.findByName(userDto.getRole().getName());
        if(role==null){

        }
        String otp = otpService.generateOtp(userDto.getEmail());
        emailService.sendOtpEmail(userDto.getEmail(), userDto.getEmail().split("@")[0], otp, "Book Store Application");
        temporaryUserService.storeTemporaryUser(userDto, otp);
    }

    @Override
    public void saveUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = UserMapper.toEntity(userDto);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public AuthResponse refreshToken(String token) {
        log.info("Inside request token");
        String userName = jwtService.extractUserName(token);
        User user = userRepository.findByUserName(userName).orElseThrow(()->new UsernameNotFoundException("User not found"));
        if(jwtService.isTokenValid(token,user)){
            var jwt = jwtService.generateToken(user);
            return AuthResponse.builder()
                    .token(jwt)
                    .refreshToken(token)
                    .username(userName)
                    .build();
        }
        return null;
    }

    @Override
    public boolean resetPassword(ResetPasswordRequest passwordRequest) {
        User user = userRepository.findByUserName(passwordRequest.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found", passwordRequest.getUserName())));

        if (!passwordRequest.getNewPassword().equals(passwordRequest.getConfirmNewPassword())) {
            log.info("New Password and confirm new password are not the same");
            return false;
        }

        if (passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            userRepository.save(user);
            return true;
        }

        log.info("Old password is incorrect");
        return false;
    }



}
