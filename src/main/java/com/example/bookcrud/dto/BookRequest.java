package com.example.bookcrud.dto;

import lombok.Getter;

@Getter
public class BookRequest {

    private String title;
    private String author;
    private Double price;
    private Long categoryId;

    public BookRequest() {}

}
