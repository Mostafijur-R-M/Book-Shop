package com.example.cse.mostafijur.bookshop;

public class Book {
    private int bookId;
    private String name;
    private String category;
    private Double price;

    public Book(int bookId, String name, String category, Double price) {
        this.bookId = bookId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Book(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
