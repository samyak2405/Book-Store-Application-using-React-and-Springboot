package com.javahunter.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Min(value = 0, message = "Quantity must be at least 0")
    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "last_updated", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
