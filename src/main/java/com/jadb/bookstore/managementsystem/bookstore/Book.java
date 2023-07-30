package com.jadb.bookstore.managementsystem.bookstore;

public class Book extends Product {

    /*
        Constructor that accepts arguments for the book's name, available number, author's name, price, and year published
        @param int books The number of books currently available
        @param double price The price of the book
        @param String name The name of the book
        @param String authorName The name of the book's author
        @param int year The year the book was published
    */
    public Book(String name, double price, int copies, String authorName, int year){
        super(name, price, copies, authorName, year);
    }
}