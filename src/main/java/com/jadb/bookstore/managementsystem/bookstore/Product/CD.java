package com.jadb.bookstore.managementsystem.bookstore.Product;

import jakarta.persistence.Entity;

@Entity
public class CD extends Product {

    /*
        Constructor that accepts arguments for the CD's name, available number, producer's name, price, and year published
        @param int CDs The number of CDs currently available
        @param double price The price of the CD
        @param String name The name of the CD
        @param String authorName The name of the CD's author
        @param int year The year the CD was published
    */
    public CD(long id, String name, double price, int copies, String producerName, int year){
        super(id, name, price, copies, producerName, year);
    }

    public CD(String name, double price, int copies, String producerName, int year){
        super(name, price, copies, producerName, year);
    }

    public CD() {

    }
}