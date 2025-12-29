package com.example.bookcrud.dto;

public class BookRequest {

    private String title;
    private String author;
    private Double price;
    private Long categoryId;

    public BookRequest() {}

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Double getPrice() {
        return price;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
