package com.example.bookcrud.dto;

import lombok.Getter;

@Getter
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private Double price;
    private String categoryName;

    public BookResponse() {}

    public BookResponse(Long id, String title, String author,
                        Double price, String categoryName) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.categoryName = categoryName;
    }

}
