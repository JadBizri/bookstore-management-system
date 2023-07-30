package com.jadb.bookstore.managementsystem.bookstore;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Bookstore implements BookstoreSpecification {

    private final ArrayList<Product> products = new ArrayList<>(); //to hold all available products
    private final ArrayList<Member> members = new ArrayList<>(); //to hold all store's members
    private final double bookPrice = 19.99; //to hold the fixed price of each book
    private final double cdPrice = 11.99; //to hold the fixed price of each CD
    private final double dvdPrice = 14.99; //to hold the fixed price of each DVD
    private int numOfNewMembers = 0; //to hold the daily number of newly registered members
    private int numOfNewPremiumMembers = 0; //to hold the daily number of newly registered premium members
    private int dailySales = 0; //to hold the number of sales made daily
    private double dailyRevenue = 0; //to hold the amount of daily revenue
    private int bookPurchased = 0; //to hold the number of books sold daily
    private int cdPurchased = 0; //to hold the number of CDs sold daily
    private int dvdPurchased = 0; //to hold the number of DVDs sold daily

    /*
        no-arg constructor
     */
    public Bookstore() {
        try {
            Scanner fileScanner = new Scanner(new File("ProductInventory.csv"));
            String line;
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine();
                if (line.startsWith("b")) {
                    String book = line.substring(5);
                    String[] string = book.split(",");
                    int copies = parseInt(string[2]);
                    double price = parseDouble(string[1]);
                    int year = parseInt(string[4]);
                    products.add(new Book(string[0], price, copies, string[3], year));
                } else if (line.startsWith("c")) {
                    String cd = line.substring(3);
                    String[] string = cd.split(",");
                    int copies = parseInt(string[2]);
                    double price = parseDouble(string[1]);
                    int year = parseInt(string[4]);
                    products.add(new CD(string[0], price, copies, string[3], year));
                } else if (line.startsWith("d")) {
                    String dvd = line.substring(4);
                    String[] string = dvd.split(",");
                    int copies = parseInt(string[2]);
                    double price = parseDouble(string[1]);
                    int year = parseInt(string[4]);
                    products.add(new DVD(string[0], price, copies, string[3], year));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Caught FileNotFoundException for ProductInventory.csv. Try again making sure the file name and path are correct.");
        }

        //default members of the store
        Member member1 = new Member("Jose", "Sierra", 1, 1, 1);
        members.add(member1);
        Member member2 = new Member("Moe", "Salah", 1, 0, 0);
        members.add(member2);

        //default premium members of the store
        Member premiumMember1 = new PremiumMember("John", "Doe", 4, 0, 0, "Visa", true);
        members.add(premiumMember1); //adding premium member to the premiumMemberList ArrayList
        Member premiumMember2 = new PremiumMember("Jane", "Doe", 1, 0, 0, "Cash", false);
        members.add(premiumMember2);
    }

    /*
        getMemberList() Method that returns the members ArrayList
        @return ArrayList members that contains all bookstore members
     */
    public ArrayList<Member> getMemberList() {
        return members;
    }

    /*
        getProducts() Method that returns the products ArrayList
        @return ArrayList products that contains all bookstore's products
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /*
        addMember() Method that adds a regular member to the members ArrayList
        @param String name The first name of the member
        @param String surname The last name of the member
        @param int books The number of books bought by the member
        @param int CDs the number of CDs bought by the member
        @param int DVDs The number of DVDs bought by the member
     */
    public void addMember(String name, String surname, int books, int CDs, int DVDs) {
        Member member = new Member(name, surname, books, CDs, DVDs);

        //to have all regular members come first in the members ArrayList
        int index = 0;
        for (Member m : members) {
            if (m instanceof PremiumMember) {
                index++; //number of premium members
            }
        }
        index = members.size() - index; //index of first premium member in the ArrayList
        members.add(index, member); //add regular member right before the premium members list starts

        numOfNewMembers++;

    }

    /*
        addPremiumMember() Method that adds a premium member to the members ArrayList
        @param String name The first name of the premium member
        @param String surname The last name of the premium member
        @param int books The number of books bought by the premium member
        @param int CDs the number of CDs bought by the premium member
        @param int DVDs The number of DVDs bought by the premium member
        @param String payMethod The method pf payment used by the premium member
        @param boolean paid Whether the membership fee has been paid or not
     */
    public void addPremiumMember(String name, String surname, int books, int CDs, int DVDs, String payMethod, boolean paid) {
        Member premiumMember = new PremiumMember(name, surname, books, CDs, DVDs, payMethod, paid);
        members.add(premiumMember);

        numOfNewPremiumMembers++;

    }

    /*
        getNumBooks() Method that returns the number of books the store has available
        @return int the number of books
     */
    public int getNumBooks() {
        int num = 0;
        for (Product x : products) {
            if (x instanceof Book) {
                num++; //number of books
            }
        }
        return num;
    }

    /*
        getNumCDs() Method that returns the number of CDs the store has available
        @return int the number of CDs
     */
    public int getNumCDs() {
        int num = 0;
        for (Product x : products) {
            if (x instanceof CD) {
                num++; //number of CDs
            }
        }
        return num;
    }

    /*
        getNumDVDs() Method that returns the number of DVDs the store has available
        @return int the number of DVDs
     */
    public int getNumDVDs() {
        int num = 0;
        for (Product x : products) {
            if (x instanceof DVD) {
                num++; //number of DVDs
            }
        }
        return num;
    }

    /*
        getNumMembers() Method that returns the number of regular members the store has
        @return int the number of regular members
     */
    public int getNumMembers() {
        int num = 0;
        for (Member x : members) {
            if (!(x instanceof PremiumMember)) {
                num++; //number of regular members
            }
        }
        return num;
    }

    /*
        getNumPremiumMembers() Method that returns the number of premium members the store has
        @return int the number of premium members
     */
    public int getNumPremiumMembers() {
        int num = 0;
        for (Member x : members) {
            if (x instanceof PremiumMember) {
                num++; //number of premium members
            }
        }
        return num;
    }

    /*
        getBookPrice() Method that returns the price of a book
        @return int the price of a book
     */
    public double getBookPrice() {
        return bookPrice;
    }

    /*
        getCDprice() Method that returns the price of a CD
        @return int the price of a CD
     */
    public double getCDprice() {
        return cdPrice;
    }

    /*
        getDVDprice() Method that returns the price of a DVD
        @return int the price of a DVD
     */
    public double getDVDprice() {
        return dvdPrice;
    }

    /*
        removeProduct() Method that removes a product from the products ArrayList
        @param index The index of the product that is to be removed from the products ArrayList
     */
    public void removeProduct(int index) {
        products.remove(index);
    }

    /*
        removeMember() Method that removes a member from the members ArrayList
        @param index The index of the member that is to be removed from the ArrayList
     */
    public void removeMember(int index) {
        members.remove(index);
    }

    /*
        startProductPurchase() Method that displays list of available products for purchase and lets user pick one to bu
        @param int loginChoice What becomes the index of the member int the members ArrayList
        @param int ProductChoice What becomes the index of the product in the products ArrayList
     */
    @Override
    public void startProductPurchase(int loginChoice, int productChoice) {

        loginChoice--; //loginChoice becomes index of member
        productChoice--; //becomes index of product

        Member temp = members.get(loginChoice);

        //int moss = getNumCDs() + getNumBooks();

        //product is a book
        if (productChoice < getNumBooks()) {
            temp.setNumOfBooksBought(temp.getNumOfBooksBought() + 1);
            dailyRevenue += bookPrice;
            bookPurchased++;
        } //if product is a CD
        else if (productChoice < (getNumCDs() + getNumBooks())) {
            temp.setNumOfCDsBought(temp.getNumOfCDsBought() + 1);
            dailyRevenue += cdPrice;
            cdPurchased++;
        } //else product is a DVD
        else {
            temp.setNumOfDVDsBought(temp.getNumOfDVDsBought() + 1);
            dailyRevenue += dvdPrice;
            dvdPurchased++;
        }

        //remove 1 book from inventory
        Product temp1 = products.get(productChoice);
        temp1.productSold();

        dailySales++;

    }

    /*
        addProduct() Method that adds a product to the products ArrayList
        @param String name The product's name
        @param double price The product's price
        @param int copies The number of copies of product
        @param String creator The product's creator's name
        @param int year The year the product was published
     */
    @Override
    public void addProduct(String name, double price, int copies, String creator, int year, int product) {

        switch (product) {

            //it's a book
            case 1 -> {
                Product book = new Book(name, price, copies, creator, year);
                //to have books come first in the products ArrayList
                int index = getNumBooks();
                products.add(index, book); //add new book after the last book in the products ArrayList
            }

            //it's a CD
            case 2 -> {
                Product cd = new CD(name, price, copies, creator, year);
                //to have all CDs come after Books in the products ArrayList
                int index = getNumBooks() + getNumCDs();
                for (Product x : products) {
                    if (x instanceof Book || x instanceof CD) {
                        index++; //number of books and CDs
                    }
                }
                products.add(index, cd);
            }

            //it's a DVD
            case 3 -> {
                Product dvd = new DVD(name, price, copies, creator, year);
                products.add(dvd); //add new DVD to the end of the products ArrayList
            }
            default -> {
            }
        }
    }

    /*
        restockProduct() Method that restocks a product with a number of copies
        @param index The index of the product in the products ArrayList
        @param moreCopies The number of additional copies to be restocked
     */
    @Override
    public void restockProduct(int index, int moreCopies) {

        index--; //becomes index of product

        Product temp = products.get(index);

        //if product is a book, CD, or DVD
        switch (temp) {
            case Book book ->
                    book.setNumCopies(book.getNumCopies() + moreCopies);
            case CD cd ->
                    cd.setNumCopies(cd.getNumCopies() + moreCopies);
            case DVD dvd ->
                    dvd.setNumCopies(dvd.getNumCopies() + moreCopies);

            default -> throw new IllegalStateException("Unexpected value: " + temp);
        }
    }

    /*
        inventoryValue() Method that calculates the entire current inventory's value and returns it
        @return double The value of the entire bookstore inventory
     */
    @Override
    public double inventoryValue() {

        //get total number of books available
        int totalBooks = 0;
        for (Product p : products) {
            if (p instanceof Book book) {
                totalBooks += book.getNumCopies();
            }
        }

        //get total number of CDs available
        int totalCDs = 0;
        for (Product p : products) {
            if (p instanceof CD cd) {
                totalCDs += cd.getNumCopies();
            }
        }

        //get total number of DVDs available
        int totalDVDs = 0;
        for (Product p : products) {
            if (p instanceof DVD dvd) {
                totalDVDs += dvd.getNumCopies();
            }
        }

        return totalBooks * bookPrice + totalCDs * cdPrice + totalDVDs * dvdPrice; //the entire available inventory value
    }

    /*
        updateInventoryFile() Method that updates the inventory file everytime there's any change in inventory
    */
    public void updateInventoryFile() {
        try {
            FileOutputStream fs = new FileOutputStream("ProductInventory.csv");
            PrintWriter outFS = new PrintWriter(fs);
            outFS.println("type,title,price,numInStock,author/artist,year");
            for (Product p : products) {
                if (p instanceof Book book) {
                    outFS.println("book," + book.getName() + "," + book.getPrice() + "," + book.getNumCopies() + ","
                            + book.getCreator() + "," + book.getYear());
                } else if (p instanceof CD cd) {
                    outFS.println("cd," + cd.getName() + "," + cd.getPrice() + "," + cd.getNumCopies() + ","
                            + cd.getCreator() + "," + cd.getYear());
                } else if (p instanceof DVD dvd) {
                    outFS.println("dvd," + dvd.getName() + "," + dvd.getPrice() + "," + dvd.getNumCopies() + ","
                            + dvd.getCreator() + "," + dvd.getYear());
                }
            }
            outFS.close();
            fs.close();
        } catch (FileNotFoundException e) {
            System.out.println("Caught FileNotFoundException for ProductInventory.csv. Try again making sure the file name and path are correct.");
        } catch (IOException e) {
            System.out.println("Caught IOException when closing output stream. Try again.");
        }
    }

    /*
        updateDailyReportFile() Method that updates the daily report file everytime before the program exits
    */
    public void updateDailyReportFile(){
        try {
            FileOutputStream fs = new FileOutputStream("DailyReport.txt");
            PrintWriter outFS = new PrintWriter(fs);
            outFS.println("Daily Report:\n");
            outFS.println("Sales:");
            outFS.println("\tBooks Sold:\t" + bookPurchased);
            outFS.println("\tCDs Sold:\t" + cdPurchased);
            outFS.println("\tDVDs Sold:\t" + dvdPurchased);
            outFS.println("\n\tTotal Sales:\t" + dailySales);
            outFS.println("\tTotal Revenue:\t$" + dailyRevenue);
            outFS.println("\n\nMemberships:");
            outFS.println("\tNewly registered regular members:\t" + numOfNewMembers);
            outFS.println("\tNewly registered premium members:\t" + numOfNewPremiumMembers);

            outFS.close();
            fs.close();
        } catch (FileNotFoundException e) {
            System.out.println("Caught FileNotFoundException for DailyReport.txt. Try again making sure the file name and path are correct.");
        } catch (IOException e) {
            System.out.println("Caught IOException when closing output stream. Try again.");
        }
    }
}
