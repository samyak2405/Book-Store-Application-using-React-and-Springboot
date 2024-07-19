package com.javahunter.application.util;

import com.javahunter.application.dto.AddressDTO;
import com.javahunter.application.entity.Address;

public class AddressMapper {

    public static AddressDTO toDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .createdAt(address.getCreatedAt())
                .updatedAt(address.getUpdatedAt())
                .build();
    }

    public static Address toEntity(AddressDTO addressDTO) {
        Address address = Address.builder()
                .addressLine1(addressDTO.getAddressLine1())
                .addressLine2(addressDTO.getAddressLine2())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .postalCode(addressDTO.getPostalCode())
                .country(addressDTO.getCountry())
                .build();
        address.setId(addressDTO.getId());
        address.setCreatedAt(addressDTO.getCreatedAt());
        address.setUpdatedAt(addressDTO.getUpdatedAt());
        return address;
    }
}
