package com.example.bookcrud.controller;

import com.example.bookcrud.dto.BookRequest;
import com.example.bookcrud.dto.BookResponse;
import com.example.bookcrud.model.Book;
import com.example.bookcrud.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /* =========================
       CREATE (ENTITY INPUT)
       ========================= */
    @PostMapping
    public Book createBook(@RequestBody BookRequest request) {
        return bookService.createBook(request);
    }

    /* =========================
       READ ALL (DTO OUTPUT)
       Supports optional filter:
       /api/books?categoryId=1
       ========================= */
    @GetMapping
    public List<BookResponse> getAllBooks(
            @RequestParam(required = false) Long categoryId
    ) {
        if (categoryId != null) {
            return bookService.getBooksByCategory(categoryId);
        }
        return bookService.getAllBooks();
    }

    /* =========================
       READ ONE (DTO OUTPUT)
       ========================= */
    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    /* =========================
       UPDATE (ENTITY INPUT)
       ========================= */
    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @RequestBody Book book
    ) {
        return bookService.updateBook(id, book);
    }

    /* =========================
       DELETE
       ========================= */
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted successfully";
    }
}
