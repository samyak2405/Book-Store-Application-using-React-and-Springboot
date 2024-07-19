package com.javahunter.application.service.impl;

import com.javahunter.application.dto.ApiResponses;
import com.javahunter.application.dto.BookDTO;
import com.javahunter.application.entity.Book;
import com.javahunter.application.exception.impl.BookAlreadyExistsException;
import com.javahunter.application.exception.impl.ResourceNotFoundException;
import com.javahunter.application.repository.BookRepository;
import com.javahunter.application.service.BookService;
import com.javahunter.application.util.BooksMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public ApiResponses addBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public ApiResponses<BookDTO> getBookById(Integer Id) {
        return null;
    }

    @Override
    public ApiResponses updateBook(Integer id, BookDTO bookDTO) {
        return null;
    }

    @Override
    public ApiResponses deleteBook(Integer id) {
        return null;
    }

    @Override
    public ApiResponses<List<BookDTO>> getAllBooks(int page, int size, String sort) {
        return null;
    }

    @Override
    public ApiResponses<List<BookDTO>> searchBooks(String query, int page, int size) {
        return null;
    }

    @Override
    public ApiResponses<List<BookDTO>> bulkAddBooks(List<BookDTO> books) {
        return null;
    }

    @Override
    public ApiResponses<List<BookDTO>> getBooksByGenre(String genre) {
        return null;
    }

    @Override
    public ApiResponses<List<BookDTO>> getBooksByAuthor(String author) {
        return null;
    }

    @Override
    public ApiResponses<List<BookDTO>> getBooksByPriceRange(Double min, Double max) {
        return null;
    }

    @Override
    public List<BookDTO> filterBooksByMultipleCreteria(String title, String author, String genre, Double priceMin, Double priceMax, LocalDate publicationDateStart, LocalDate publicationDateEnd) {
        return null;
    }
}
