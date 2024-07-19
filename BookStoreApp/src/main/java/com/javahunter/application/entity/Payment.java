package com.javahunter.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin("0.00")
    private BigDecimal amount;

    @NotBlank(message = "Payment method is mandatory")
    @Size(max = 50, message = "Payment method must be at most 50 characters")
    private String paymentMethod;

    @NotBlank(message = "Payment status is mandatory")
    @Size(max = 50, message = "Payment status must be at most 50 characters")
    private String paymentStatus;

    @Column(name = "payment_date", nullable = false, updatable = false)
    private LocalDateTime paymentDate;
}
