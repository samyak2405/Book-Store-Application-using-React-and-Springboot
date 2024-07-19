package com.javahunter.application.controller;

import com.javahunter.application.dto.BookDTO;
import com.javahunter.application.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * BookController is responsible for handling operations related to books
 * such as adding, updating, retrieving, and deleting books. It also provides
 * functionalities to upload book data from a CSV file and filter books by category.
 *
 * <p>This controller interacts with the BookService to perform the necessary
 * business logic and uses BookDTO to transfer book data across different layers
 * of the application. Logging is incorporated to facilitate tracking and debugging
 * of operations.</p>
 *
 * <p>Endpoints:</p>
 * <ul>
 * <li><code>/api/v1/book/add</code>: Adds a new book to the collection.</li>
 * <li><code>/api/v1/book/upload</code>: Uploads books from a CSV file.</li>
 * <li><code>/api/v1/book/get/{bookId}</code>: Retrieves a book by its ID.</li>
 * <li><code>/api/v1/book/update/{bookId}</code>: Updates an existing book's details.</li>
 * <li><code>/api/v1/book/delete/{bookId}</code>: Deletes a book by its ID.</li>
 * <li><code>/api/v1/book/get-all</code>: Retrieves all books in the collection.</li>
 * <li><code>/api/v1/book/get/by/category/{category}</code>: Retrieves books by category.</li>
 * </ul>
 *
 * <p>HTTP Status Codes:</p>
 * <ul>
 * <li><code>200 OK</code>: The request was successful.</li>
 * <li><code>400 BAD REQUEST</code>: The request was invalid or failed.</li>
 * <li><code>404 NOT FOUND</code>: The requested resource was not found.</li>
 * </ul>
 *
 * <p>Dependencies:</p>
 * <ul>
 * <li>BookService: Handles the core business logic for book operations.</li>
 * <li>BookDTO: Data transfer object representing book information.</li>
 * </ul>
 *
 * @see BookService
 * @see BookDTO
 */
@RequestMapping("/api/v1/book")
@RestController
@RequiredArgsConstructor
public class BookController {


}
