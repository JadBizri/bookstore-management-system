package com.jadb.bookstore.managementsystem.bookstore;

public class DVD extends Product {

    /*
        Constructor that accepts arguments for the DVD's name, available number, director's name, price, and year published
        @param int DVDs The number of DVDs currently available
        @param double price The price of the DVD
        @param String name The name of the DVD
        @param String directorName The name of the DVD's director
        @param int year The year the DVD was published
    */
    public DVD(long id, String name, double price, int copies, String directorName, int year){
        super(id, name, price, copies, directorName, year);
    }
}