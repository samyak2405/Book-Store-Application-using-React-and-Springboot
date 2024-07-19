package com.javahunter.application.service.impl;

import com.javahunter.application.dto.AdminDto;
import com.javahunter.application.dto.UserDto;
import com.javahunter.application.entity.User;
import com.javahunter.application.exception.impl.ResourceNotFoundException;
import com.javahunter.application.repository.UserRepository;
import com.javahunter.application.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    private final UserRepository userRepository;
    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        return null;
    }

    @Override
    public AdminDto updateAdmin(Long adminId, AdminDto adminDto) {
        return null;
    }

    @Override
    public void deleteAdmin(Long adminId) {

    }

    @Override
    public AdminDto getAdminById(Long adminId) {
        return null;
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        if(users.isEmpty())
//            throw new ResourceNotFoundException("No User Found");
//        return users.stream()
//                .map(ModelMapperUtil::convertToUserDto)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public boolean disableUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with Id %s not present", userId)));
        user.setEnabled(false);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean enableUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with Id %s not present", userId)));
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with Id %s not present", userId)));

//        return ModelMapperUtil.convertToUserDto(user);
        return null;
    }

}
