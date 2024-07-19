package com.javahunter.application.util;

import com.javahunter.application.dto.UserDto;
import com.javahunter.application.entity.Address;
import com.javahunter.application.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserMapper {

    public static UserDto toDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userName(user.getUsername())
                .password(user.getPassword())
                .enabled(user.getEnabled())
                .role(RoleMapper.toDTO(user.getRole()))
                .addresses(user.getAddresses().stream().map(AddressMapper::toDTO).collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());

        // Do not set role here, it will be set in the service method
        // user.setRole(userDto.getRole());

        // Set addresses if necessary
        Set<Address> addresses = userDto.getAddresses().stream()
                .map(addressDto -> {
                    Address address = new Address();
                    address.setAddressLine1(addressDto.getAddressLine1());
                    address.setAddressLine2(addressDto.getAddressLine2());
                    address.setCity(addressDto.getCity());
                    address.setState(addressDto.getState());
                    address.setPostalCode(addressDto.getPostalCode());
                    address.setCountry(addressDto.getCountry());
                    address.setUser(user);
                    return address;
                })
                .collect(Collectors.toSet());
        user.setAddresses(addresses);

        return user;
    }


}
