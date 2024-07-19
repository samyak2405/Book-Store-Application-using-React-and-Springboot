package com.javahunter.application.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BookDTO {

    private Integer id;

    @NotBlank(message = "Book name is mandatory")
    @Size(max = 255, message = "Book name must be at most 255 characters")
    private String name;

//    @NotBlank(message = "Author name is mandatory")
    @Size(max = 255, message = "Author name must be at most 255 characters")
    private String author;

    @Size(max = 50, message = "Format must be at most 50 characters")
    private String format;

    @DecimalMax("5.00")
    @DecimalMin("0.00")
    private BigDecimal bookDepositoryStars;

//    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.00", message = "Price must be at least 0.00")
    private BigDecimal price;

//    @NotBlank(message = "Currency is mandatory")
    @Size(max = 10, message = "Currency must be at most 10 characters")
    private String currency;

    @DecimalMin(value = "0.00", message = "Old price must be at least 0.00")
    private BigDecimal oldPrice;

    @Size(max = 20, message = "ISBN must be at most 20 characters")
    private String isbn;

    @Size(max = 100, message = "Category must be at most 100 characters")
    private String category;

    private Integer inventoryCount;

    private String imagePath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
