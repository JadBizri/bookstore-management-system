package com.jadb.bookstore.managementsystem.bookstore;

public abstract class Product implements Comparable<Product> {

    private int copies; //to hold number of copies of product available
    private double price; //to hold product price
    private String name; //to hold product's name
    private String creator; //to hold product's creator's name
    private int year; //to hold the year the product was published
    private Long id; //to hold the product's id

    /*
        Constructor that accepts arguments for the product's name, available copies, creator's name, price, and year published
        @param Long id The id of the product
        @param int copies The number of copies currently available
        @param double price The price of the product
        @param String name The name of the product
        @param String creator The name of the product's creator
        @param int The year the product was published
    */
    public Product(Long id, String name, double price, int copies, String creator, int year) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.copies = copies;
        this.creator = creator;
        this.year = year;
    }

    /*
        getId() Method that returns the product's id
        @return Long id The product's id
    */
    public Long getId() {
        return id;
    }

    /*
        setId() Method that sets the product's id
        @param Long id The product's id
    */
    public void setId(Long id) {
        this.id = id;
    }

    /*
        getName() Method that returns the product's name
        @return String name The product's name
    */
    public String getName() {
        return name;
    }

    /*
        setBookName() Method that sets the product's name
        @param String name The product's name
    */
    public void setName(String name) {
        this.name = name;
    }

    /*
        getNumCopies() Method that returns the number of copies available
        @return int copies The number of copies available
    */
    public int getNumCopies() {
        return copies;
    }

    /*
        setNumBooks() Method that sets the number of copies available
        @param int copies The number of copies to be set
    */
    public void setNumCopies(int copies) {
        this.copies = copies;
    }

    /*
        getPrice() Method that returns the product's price
        @return double price The product's price
    */
    public double getPrice() {
        return price;
    }

    /*
        setPrice() Method that sets the product's price
        @param double price The product's price to be set
    */
    public void setPrice(double price) {
        this.price = price;
    }

    /*
        getCreator() Method that returns the product's creator name
        @return String creator The product's creator name
    */
    public String getCreator() {
        return creator;
    }

    /*
        setCreator() Method that sets the product's creator name
        @param String creator The product's creator's name to be set
    */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /*
        getYear() Method that returns the product's year published
        @return int year The product's year published
    */
    public int getYear() {
        return year;
    }

    /*
        setYear() Method that sets the product's year published
        @param int year The product's year published
    */
    public void setYear(int year) {
        this.year = year;
    }

    /*
        compareTo() Method that compares products based on their price
        @param Product p A product object
    */
    @Override
    public int compareTo(Product p) {
        //if products are not the same
        if (this.getPrice() != p.getPrice()) {
            return -1;
        }
        //if products are the same
        else {
            return 0;
        }
    }
}
