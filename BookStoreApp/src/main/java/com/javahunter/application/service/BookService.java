package com.javahunter.application.service;

import com.javahunter.application.dto.ApiResponses;
import com.javahunter.application.dto.BookDTO;
import com.javahunter.application.exception.impl.BookAlreadyExistsException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    public ApiResponses addBook(BookDTO bookDTO);
    public ApiResponses<BookDTO> getBookById(Integer Id);

    public ApiResponses updateBook(Integer id, BookDTO bookDTO);

    public ApiResponses deleteBook(Integer id);

    public ApiResponses<List<BookDTO>> getAllBooks(int page, int size, String sort);

    public ApiResponses<List<BookDTO>> searchBooks(String query,int page,int size);

    public ApiResponses<List<BookDTO>> bulkAddBooks(List<BookDTO> books);

    public ApiResponses<List<BookDTO>> getBooksByGenre(String genre);

    public ApiResponses<List<BookDTO>> getBooksByAuthor(String author);

    public ApiResponses<List<BookDTO>> getBooksByPriceRange(Double min, Double max);

    public List<BookDTO> filterBooksByMultipleCreteria(
            String title,
            String author,
            String genre,
            Double priceMin,
            Double priceMax,
            LocalDate publicationDateStart,
            LocalDate publicationDateEnd
    );
}
