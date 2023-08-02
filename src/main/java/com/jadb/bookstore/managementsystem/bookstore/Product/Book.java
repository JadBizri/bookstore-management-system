package com.jadb.bookstore.managementsystem.bookstore.Product;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class Book extends Product {

    private String isbn; //to hold book isbn
    /*
        Constructor that accepts arguments for the book's name, available number, author's name, price, and year published
        @param int books The number of books currently available
        @param double price The price of the book
        @param String name The name of the book
        @param String authorName The name of the book's author
        @param int year The year the book was published
    */
    public Book(long id, String name, double price, int copies, String authorName, int year, String isbn){
        super(id, name, price, copies, authorName, year);
        this.isbn = isbn;
    }

    public Book(String name, double price, int copies, String authorName, int year, String isbn){
        super(name, price, copies, authorName, year);
        this.isbn = isbn;
    }

    //Empty Constructor
    public Book() {}

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}