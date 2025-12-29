package com.example.bookcrud.repository;

import com.example.bookcrud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Find all books that belong to a specific category
    List<Book> findByCategoryId(Long categoryId);
}
