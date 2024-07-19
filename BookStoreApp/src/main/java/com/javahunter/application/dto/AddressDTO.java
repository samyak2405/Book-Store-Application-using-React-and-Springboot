package com.javahunter.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AddressDTO {

    private Integer id;

    @Schema(description = "Address line 1",example = "Krish Homes PG, 1st cross road")
    @NotBlank(message = "Address Line 1 is mandatory")
    @Size(max = 255, message = "Address Line 1 should not be more than 255 characters")
    private String addressLine1;

    @Schema(description = "Address line 2 is optional",example = "1st main road, Indiranagar")
    @Size(max = 255, message = "Address Line 2 should not be more than 255 characters")
    private String addressLine2;

    @Schema(description = "City in which you live",example = "Bengaluru")
    @NotBlank(message = "City is mandatory")
    @Size(max = 100, message = "City should not be more than 100 characters")
    private String city;

    @Schema(description = "State in which you live",example = "Karnataka")
    @NotBlank(message = "State is mandatory")
    @Size(max = 100, message = "State should not be more than 100 characters")
    private String state;

    @Schema(description = "Postal code of your area",example = "560038")
    @NotBlank(message = "Postal Code is mandatory")
    @Size(max = 20, message = "Postal Code should not be more than 20 characters")
    private String postalCode;

    @Schema(description = "Country in which you live",example = "India")
    @NotBlank(message = "Country is mandatory")
    @Size(max = 100, message = "Country should not be more than 100 characters")
    private String country;

    @Schema(description = "Time at which the address was created",example = "2024-06-23 19:31:25")
    private LocalDateTime createdAt;

    @Schema(description = "Time at which the address was updated",example = "2024-06-23 19:31:25")
    private LocalDateTime updatedAt;

}
