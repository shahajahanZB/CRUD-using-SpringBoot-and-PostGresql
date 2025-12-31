package com.example.bookcrud.controller;

import com.example.bookcrud.dto.BookRequest;
import com.example.bookcrud.dto.BookResponse;
import com.example.bookcrud.model.Book;
import com.example.bookcrud.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ✅ PAGINATED LIST OF ALL BOOKS
    @GetMapping
    public Page<BookResponse> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return bookService.getBooksPaginated(page, size);
    }

    // ✅ PAGINATED BOOKS BY CATEGORY
    @GetMapping("/category/{categoryId}")
    public Page<BookResponse> getBooksByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return bookService.getBooksByCategory(categoryId, page, size);
    }

    @PostMapping
    public Book createBook(@RequestBody BookRequest request) {
        return bookService.createBook(request);
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @RequestBody Book book
    ) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted successfully";
    }
}

