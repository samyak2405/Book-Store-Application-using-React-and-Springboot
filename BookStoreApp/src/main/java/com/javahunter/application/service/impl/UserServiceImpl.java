package com.javahunter.application.service.impl;

import com.javahunter.application.dto.UserDto;
import com.javahunter.application.entity.User;
import com.javahunter.application.exception.impl.ResourceNotFoundException;
import com.javahunter.application.repository.UserRepository;
import com.javahunter.application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with Id %s not present", userId)));

//        return ModelMapperUtil.convertToUserDto(user);
        return null;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                return userRepository.findByUserName(userName)
                        .orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
        };
    }
}
