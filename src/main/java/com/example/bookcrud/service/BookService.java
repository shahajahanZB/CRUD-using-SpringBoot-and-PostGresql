package com.example.bookcrud.service;

import com.example.bookcrud.dto.BookRequest;
import com.example.bookcrud.dto.BookResponse;
import com.example.bookcrud.model.Book;
import com.example.bookcrud.model.Category;
import com.example.bookcrud.repository.BookRepository;
import com.example.bookcrud.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public Page<BookResponse> getBooksPaginated(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return bookRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

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

    public Page<BookResponse> getBooksByCategory(Long categoryId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return bookRepository.findByCategoryId(categoryId, pageable)
                .map(this::mapToResponse);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private BookResponse mapToResponse(Book book) {

        String categoryName = null;

        if (book.getCategory() != null) {
            categoryName = book.getCategory().getName();
        }

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                categoryName
        );
    }
}
