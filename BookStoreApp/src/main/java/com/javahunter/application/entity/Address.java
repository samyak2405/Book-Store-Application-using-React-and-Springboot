package com.javahunter.application.entity;

import com.javahunter.application.entity.enums.AddressType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Address Line 1 is mandatory")
    @Size(max = 255, message = "Address Line 1 must be at most 255 characters")
    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Size(max = 255, message = "Address Line 2 must be at most 255 characters")
    @Column(name = "address_line2")
    private String addressLine2;

    @NotBlank(message = "City is mandatory")
    @Size(max = 100, message = "City must be at most 100 characters")
    private String city;

    @NotBlank(message = "State is mandatory")
    @Size(max = 100, message = "State must be at most 100 characters")
    private String state;

    @NotBlank(message = "Postal Code is mandatory")
    @Size(max = 20, message = "Postal Code must be at most 20 characters")
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @NotBlank(message = "Country is mandatory")
    @Size(max = 100, message = "Country must be at most 100 characters")
    private String country;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
