package com.jadb.bookstore.managementsystem.bookstore;

public interface BookstoreSpecification {

    /*
        restockProduct() Method that restocks a product with a number of copies
        @param index The index of the product in the products ArrayList
        @param moreCopies The number of additional copies to be restocked
     */
    public void restockProduct(int index, int moreCopies);


    /*
        startProductPurchase() Method that displays list of available products for purchase and lets user pick one to buy
        @param int loginChoice What becomes the index of the member int the members ArrayList
        @param int ProductChoice What becomes the index of the product in the products ArrayList
     */
    public void startProductPurchase(int loginChoice, int productChoice);


    /*
        inventoryValue() Method that calculates the entire current inventory's value and returns it
        @return the value of the entire bookstore inventory
     */
    public double inventoryValue();


    /*
        addProduct() Method that adds a new product to inventory
        @param String name The product's name
        @param double price The product's price
        @param int copies The number of copies of product
        @param String creator The product's creator's name
        @param int year The year the product was published
     */
    public void addProduct(String name, double price, int copies, String creator, int year, int product);
}
