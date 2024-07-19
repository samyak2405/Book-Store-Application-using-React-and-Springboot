package com.javahunter.application.util;

import com.javahunter.application.dto.BookDTO;
import com.javahunter.application.entity.Book;

public class BooksMapper {

    public static BookDTO toDto(Book book) {
        if (book == null) {
            return null;
        }

        return BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .format(book.getFormat())
                .bookDepositoryStars(book.getBookDepositoryStars())
                .price(book.getPrice())
                .currency(book.getCurrency())
                .oldPrice(book.getOldPrice())
                .isbn(book.getIsbn())
                .category(book.getCategory())
                .inventoryCount(book.getInventoryCount())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }
    public static Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }

        return Book.builder()
                .name(bookDTO.getName())
                .author(bookDTO.getAuthor())
                .format(bookDTO.getFormat())
                .bookDepositoryStars(bookDTO.getBookDepositoryStars())
                .price(bookDTO.getPrice())
                .currency(bookDTO.getCurrency())
                .oldPrice(bookDTO.getOldPrice())
                .isbn(bookDTO.getIsbn())
                .category(bookDTO.getCategory())
                .inventoryCount(bookDTO.getInventoryCount())
                .createdAt(bookDTO.getCreatedAt())
                .updatedAt(bookDTO.getUpdatedAt())
                .build();
    }
}
