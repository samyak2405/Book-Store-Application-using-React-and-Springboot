package com.javahunter.application.repository;

import com.javahunter.application.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    boolean existsByIsbn(String isbn);

    List<Book> findByCategory(String category);
}
