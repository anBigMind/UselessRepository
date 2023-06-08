package com.oracle.entity;

import java.io.Serializable;

public class Book implements Serializable {
    private String ISBN;
    private String bookName;
    private String publishingHoush;
    private int bookTypeId;
    private String author;
    private String bookTypeName;

    public Book() {
    }

    public Book(String ISBN, String bookName, String publishingHoush, int bookTypeId, String author, String bookTypeName) {
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.publishingHoush = publishingHoush;
        this.bookTypeId = bookTypeId;
        this.author = author;
        this.bookTypeName = bookTypeName;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublishingHoush() {
        return publishingHoush;
    }

    public void setPublishingHoush(String publishingHoush) {
        this.publishingHoush = publishingHoush;
    }

    public int getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(int bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
