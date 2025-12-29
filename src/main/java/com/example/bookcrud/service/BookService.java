package com.example.bookcrud.service;

import com.example.bookcrud.dto.BookRequest;
import com.example.bookcrud.dto.BookResponse;
import com.example.bookcrud.model.Book;
import com.example.bookcrud.model.Category;
import com.example.bookcrud.repository.BookRepository;
import com.example.bookcrud.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository,
                       CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    /* =========================
       CREATE
       ========================= */
    public Book createBook(BookRequest request) {

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());
        book.setCategory(category);

        return bookRepository.save(book);
    }

    /* =========================
       READ (DTO RESPONSES)
       ========================= */

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return mapToResponse(book);
    }

    public List<BookResponse> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /* =========================
       UPDATE
       ========================= */
    public Book updateBook(Long id, Book updatedBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());

        return bookRepository.save(book);
    }

    /* =========================
       DELETE
       ========================= */
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    /* =========================
       MAPPER (ENTITY → DTO)
       ========================= */
    private BookResponse mapToResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getCategory().getName()
        );
    }
}
