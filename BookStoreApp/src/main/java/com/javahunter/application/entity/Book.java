package com.javahunter.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Book name is mandatory")
    @Size(max = 255, message = "Book name must be at most 255 characters")
    @Field(type = FieldType.Text)
    private String name;

    @NotBlank(message = "Author name is mandatory")
    @Size(max = 255, message = "Author name must be at most 255 characters")
    @Field(type = FieldType.Text)
    private String author;

    @Size(max = 50, message = "Format must be at most 50 characters")
    @Field(type = FieldType.Text)
    private String format;

    @DecimalMax("5.00")
    @DecimalMin("0.00")
    @Field(type = FieldType.Scaled_Float,scalingFactor = 100)
    private BigDecimal bookDepositoryStars;

    @NotNull(message = "Price is mandatory")
    @DecimalMin( value = "0.00", message = "Price must be at least 0.00")
    @Field(type = FieldType.Scaled_Float,scalingFactor = 100)
    private BigDecimal price;

    @NotBlank(message = "Currency is mandatory")
    @Size(max = 10, message = "Currency must be at most 10 characters")
    @Field(type = FieldType.Text)
    private String currency;

    @DecimalMin(value = "0.00", message = "Old price must be at least 0.00")
    @Field(type = FieldType.Scaled_Float,scalingFactor = 100)
    private BigDecimal oldPrice;

    @Size(max = 20, message = "ISBN must be at most 20 characters")
    @Column(unique = true)
    @Field(type = FieldType.Text)
    private String isbn;

    @Size(max = 100, message = "Category must be at most 100 characters")
    @Field(type = FieldType.Text)
    private String category;

    @Field(type = FieldType.Integer)
    private Integer inventoryCount = 5;

    @Field(type = FieldType.Text)
    private String imagePath;

    @Column(updatable = false)
    @CreationTimestamp
    @Field(type = FieldType.Date)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Field(type = FieldType.Date)
    private LocalDateTime updatedAt;
}
