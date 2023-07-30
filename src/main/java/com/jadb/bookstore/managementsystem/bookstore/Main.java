package com.jadb.bookstore.managementsystem.bookstore;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in); //scanner to get user input

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Welcome to the Bookstore System!"); //welcome message

        //creating a bookstore object
        Bookstore store = new Bookstore();

        boolean storeOpen = true; //keep the program running while store is open

        //while loop that loops menu options until program is exited by user
        while (storeOpen == true) {

            //calling the store.getMemberList() method to get the list of current bookstore members
            ArrayList<Member> memberList = store.getMemberList();

            //calling the displayMainMenu() Method to display the main menu and return user's choice
            int choice = displayMainMenu();

            //switch on the choice variable to determine what to do after user's choice
            switch (choice) {

                //make a purchase
                case 1 ->
                        startPurchase(store, memberList);

                //manage inventory
                case 2 ->
                        manageInventory(store);

                //manage member accounts
                case 3 ->
                        manageAccounts(store, memberList);

                //exit program
                case 4 -> {//end whileloop
                    store.updateDailyReportFile();
                    store.updateInventoryFile();
                    storeOpen = false;
                }
                //test harness for compareTo() Method
                case 5 -> {
                    System.out.println("\nChoose first product to compare with:");
                    ArrayList<Product> temp = store.getProducts();
                    int i = 1;
                    for (Product p : temp) {
                        switch (p) {
                            case Book book ->
                                    System.out.println("\t" + i + ". Book: " + book.getName() + " by " + book.getCreator() + " (" + book.getYear() + ") (" + book.getNumCopies() + " in stock)");
                            case CD cd ->
                                    System.out.println("\t" + i + ". CD: " + cd.getName() + " by " + cd.getCreator() + " (" + cd.getYear() + ") (" + cd.getNumCopies() + " in stock)");
                            case DVD dvd ->
                                    System.out.println("\t" + i + ". DVD: " + dvd.getName() + " (" + dvd.getYear() + ") (" + dvd.getNumCopies() + " in stock)");

                            default -> throw new IllegalStateException("Unexpected value: " + p);
                        }
                        i++;
                    }
                    System.out.print("Enter a number: ");
                    int choice1 = sc.nextInt();
                    while (choice1 < 1 || choice1 > temp.size()) {
                        System.out.print("Try again: ");
                        choice1 = sc.nextInt();
                    }
                    Product product1 = temp.get(choice1 - 1);
                    System.out.println("\nChoose second product to compare with:");
                    int j = 1;
                    for (Product p : temp) {
                        switch (p) {
                            case Book book ->
                                    System.out.println("\t" + j + ". Book: " + book.getName() + " by " + book.getCreator() + " (" + book.getYear() + ") (" + book.getNumCopies() + " in stock)");
                            case CD cd ->
                                    System.out.println("\t" + j + ". CD: " + cd.getName() + " by " + cd.getCreator() + " (" + cd.getYear() + ") (" + cd.getNumCopies() + " in stock)");
                            case DVD dvd ->
                                    System.out.println("\t" + j + ". DVD: " + dvd.getName() + " (" + dvd.getYear() + ") (" + dvd.getNumCopies() + " in stock)");

                            default -> throw new IllegalStateException("Unexpected value: " + p);
                        }
                        j++;
                    }
                    System.out.print("Enter a number: ");
                    int choice2 = sc.nextInt();
                    while (choice2 < 1 || choice2 > temp.size()) {
                        System.out.print("Try again: ");
                        choice2 = sc.nextInt();
                    }
                    Product product2 = temp.get(choice2 - 1);
                    int result = product1.compareTo(product2);
                    if (result == 1) {
                        System.out.println("\nTHEY ARE THE SAME TYPE OF PRODUCT!");
                    } else {
                        System.out.println("\nThey're different products.");
                    }
                }

                //displays error message if user inputs something wrong
                default ->
                        System.out.println("\nSorry, but you need to enter a number between 1-5.\n");
            }
        }
        //end of while loop exit message
        System.out.println("\nThank you for using our bookstore program!");

        sc.close(); //close scanner
    }
    //end of main

    /*
        displayMainMenu() Method that displays the main menu and returns user's menu choice
        @return int The user's menu choice
     */
    public static int displayMainMenu() {
        //menu items
        System.out.println("\nPlease choose one of the following menu options:");
        System.out.println("\t1. Make a Purchase");
        System.out.println("\t2. Manage Inventory");
        System.out.println("\t3. Manage Accounts");
        System.out.println("\t4. Exit");

        System.out.println("\n\t5. Testing Comparing Products");
        System.out.print("Enter a number 1-5: ");

        return sc.nextInt(); //return user's choice
    }

    /*
        displayLoginMenu() Method that displays the login menu and returns user's choice
        @param addProduct store The bookstore class that contains the ArrayList of members
        @return int loginChoice The user's menu choice
     */
    public static int displayLoginMenu(Bookstore store, ArrayList<Member> memberList) {

        System.out.println("\nPlease select a member account or create a new one:");

        int i = 1; //to correctly number member list

        //for loop that loops through the memberList ArrayList and prints out each member's name
        for (Member member : memberList) {
            System.out.println("\t" + i + ". " + member.getFirstName() + " " + member.getLastName());
            i++;
        }

        System.out.println("\t" + (memberList.size() + 1) + ". Create a new member account");
        System.out.print("Enter a number 1-" + (memberList.size() + 1) + ": ");
        int loginChoice = sc.nextInt(); //to hold user's login choice

        //validate user's input
        while (loginChoice < 1 || loginChoice > (memberList.size() + 1)) {
            System.out.println("\nSorry, but you have to enter a valid number.");
            System.out.println("\nPlease select a user or create a new one:");
            i = 1;
            for (Member member : memberList) {
                System.out.println("\t" + i + ". " + member.getFirstName() + " " + member.getLastName());
                i++;
            }
            System.out.println("\t" + (memberList.size() + 1) + ". Create a new member account");
            System.out.print("Enter a number 1-" + (memberList.size() + 1) + ": ");
            loginChoice = sc.nextInt();
        }
        return loginChoice;
    }

    /*
        displayPurchaseMenu() Method that displays the purchase menu and returns user's menu choice
        @param addProduct store To get live numbers of current books, CDs, and DVDs available
        @return int The user's menu choice
     */
    public static int displayPurchaseMenu(Bookstore store) {
        //menu items
        System.out.println("\nWhat would they like to purchase?");
        System.out.println("\t1. Books (" + store.getNumBooks() + " available)");
        System.out.println("\t2. CDs (" + store.getNumCDs() + " available)");
        System.out.println("\t3. DVDs (" + store.getNumDVDs() + " available)");
        System.out.print("Enter a number 1-3: ");
        int menuChoice = sc.nextInt(); //to hold user's choice

        //while loop to validate user's input
        while (menuChoice < 1 || menuChoice > 3) {
            System.out.println("\nSorry, but you need to enter a number between 1-3.\n");
            System.out.println("\nWhat would you like to purchase?");
            System.out.println("\t1. Books (" + store.getNumBooks() + " available)");
            System.out.println("\t2. CDs (" + store.getNumCDs() + " available)");
            System.out.println("\t3. DVDs (" + store.getNumDVDs() + " available)");
            System.out.print("Enter a number 1-3: ");
            menuChoice = sc.nextInt();
        }
        return menuChoice;
    }

    /*
        buyMore() Method that asks the user if the same member would want to continue adding items to buy or complete purchase and returns a boolean value
        @return boolean Whether the same member would want to continue adding items or not
     */
    public static boolean buyMore() {
        System.out.println("\nItem successfully scanned!\n\t1. Complete Purchase\n\t2. Add another item to be purchased by the same member");
        System.out.print("Enter a number (1-2): ");
        int purchaseChoice = sc.nextInt();
        while (purchaseChoice < 1 || purchaseChoice > 2) {
            System.out.println("\nSorry, but you must enter either a 1 or a 2.");
            System.out.println("\nItem successfully scanned!\n\t1. Complete Purchase\n\t2. Add another item to be purchased by the same member");
            System.out.print("Enter a number (1-2): ");
            purchaseChoice = sc.nextInt();
        }
        if (purchaseChoice == 2) {
            return true;
        } ///print success message
        else {
            System.out.println("\n--------------------------------------------------------");
            System.out.println("Purchase was successful!");
            System.out.println("--------------------------------------------------------");
            return false;
        }
    }

    /*
        premiumMembership() Method that asks user if the member wants a premium membership or not and returns a boolean value whether they want it or not
        @return boolean Whether the member wants the premium membership or not
     */
    public static boolean premiumMembership() {
        //asking if member wants a premium membership with while loop to validate user's input
        boolean error = true;
        while (error == true) {
            System.out.print("Premium Membership (y/n): ");
            String membership = sc.nextLine();

            //if yes, then method returns true
            if (membership.equalsIgnoreCase("y") || membership.equalsIgnoreCase("yes")) {
                return true; //user wants a premium membership
            } //if user makes a wrong input, print error message and repeat question
            else if (!membership.equalsIgnoreCase("n") && !membership.equalsIgnoreCase("no")) {
                System.out.println("\nERROR: Please Enter Yes or No\n");
            } //else, the only possibility left is "no" or "n", so return false
            else {
                return false;
            }
        }
        return false;
    }

    /*
        payMethod() Method that asks premium member what their preferred payment method is and returns a String of that payment method
        @return String The payment method
     */
    public static String payMethod() {
        //print out payment method menu
        System.out.println("\nWhat's your preferred method of payment?");
        System.out.println("\t1. Visa");
        System.out.println("\t2. Mastercard");
        System.out.println("\t3. Discover");
        System.out.println("\t4. American Express");
        System.out.println("\t5. Cash");
        System.out.print("Enter a number (1-5): ");
        int choice = sc.nextInt(); //to hold user's choice
        //validate user response
        while (choice < 1 || choice > 5) {
            System.out.println("\nSorry, but you have to enter a number between 1-5");
            System.out.println("\nWhat's your preferred method of payment?");
            System.out.println("\t1. Visa");
            System.out.println("\t2. Mastercard");
            System.out.println("\t3. Discover");
            System.out.println("\t4. American Express");
            System.out.println("\t5. Cash");
            System.out.print("Enter a number (1-5): ");
            choice = sc.nextInt();
        }

        //switch on the choice variable
        switch (choice) {
            case 1 -> {
                return "Visa";
            }
            case 2 -> {
                return "Mastercard";
            }
            case 3 -> {
                return "Discover";
            }
            case 4 -> {
                return "American Express";
            }
            case 5 -> {
                return "Cash";
            }
            default -> {
            }
        }
        return "-1";
    }

    /*
        getBookChoice() Method that asks user what book they would like to buy and returns user's choice
        @param addProduct store The bookstore class that contains the ArrayList of books
        @return int bookChoice The user's choice of book
     */
    public static int getBookChoice(Bookstore store) {
        System.out.println("\nWhich book would they like to buy? [$19.99 each]");
        getListOfBooks(store); //to display list of available books
        System.out.print("Enter a number: ");
        int bookChoice = sc.nextInt(); //to hold the book picked by user
        //while loop to validate user input
        while (bookChoice <= 0 || bookChoice > store.getNumBooks()) {
            System.out.println("\nSorry, but you have to choose one of the available options.");
            System.out.println("\nWhich book would they like to buy? [$19.99 each]");
            getListOfBooks(store);
            System.out.print("Enter a number: ");
            bookChoice = sc.nextInt();
        }
        ArrayList<Product> temp = store.getProducts();
        Product p = temp.get(bookChoice - 1);
        if (p.getNumCopies() == 0) {
            System.out.println("\nSorry, but we don't have any more copies of this book!");
            return -1;
        }
        return bookChoice;
    }

    /*
        getCDchoice() Method that asks user what CD they would like to buy and returns user's choice
        @param addProduct store The bookstore class that contains the ArrayList of CDs
        @return int cdChoice The user's choice of CD
     */
    public static int getCDchoice(Bookstore store) {
        System.out.println("\nWhich CD would they like to buy? [$11.99 each]");
        getListOfCDs(store); //to display list of available CDs
        System.out.print("Enter a number: ");
        int cdChoice = sc.nextInt(); //to hold the CD picked by user
        //while loop to validate user input
        while (cdChoice <= 0 || cdChoice > store.getNumCDs()) {
            System.out.println("\nSorry, but you have to choose one of the available options.");
            System.out.println("\nWhich CD would they like to buy? [$11.99 each]");
            getListOfCDs(store);
            System.out.print("Enter a number: ");
            cdChoice = sc.nextInt();
        }
        ArrayList<Product> temp = store.getProducts();
        Product p = temp.get(cdChoice + store.getNumBooks() - 1);
        if (p.getNumCopies() == 0) {
            System.out.println("\nSorry, but we don't have any more copies of this CD!");
            return -1;
        }
        return cdChoice + store.getNumBooks();
    }

    /*
        getDVDchoice() Method that asks user what DVD they would like to buy and returns user's choice
        @param addProduct store The bookstore class that contains the ArrayList of DVDs
        @return int dvdChoice The user's choice of DVD
     */
    public static int getDVDchoice(Bookstore store) {
        System.out.println("\nWhich DVD would they like to buy? [$14.99 each]");
        getListOfDVDs(store); //to display list of available DVDs
        System.out.print("Enter a number: ");
        int dvdChoice = sc.nextInt(); //to hold the DVD picked by user
        //while loop to validate user input
        while (dvdChoice <= 0 || dvdChoice > store.getNumDVDs()) {
            System.out.println("\nSorry, but you have to choose one of the available options.");
            System.out.println("\nWhich DVD would they like to buy? [$14.99 each]");
            getListOfDVDs(store);
            System.out.print("Enter a number: ");
            dvdChoice = sc.nextInt();
        }
        ArrayList<Product> temp = store.getProducts();
        Product p = temp.get(dvdChoice + store.getNumBooks() + store.getNumCDs() - 1);
        if (p.getNumCopies() == 0) {
            System.out.println("\nSorry, but we don't have any more copies of this DVD!");
            return -1;
        }
        return dvdChoice + store.getNumBooks() + store.getNumCDs();
    }

    /*
        displayInventory() Method that displays current bookstore inventory
        @param addProduct store The bookstore class that contains the ArrayList of books, CDs, and DVDs
     */
    public static void displayInventory(Bookstore store) {
        System.out.println("\nWe currently have:");
        System.out.println("\n" + store.getNumBooks() + " book(s)");
        getListOfBooks(store);
        System.out.println("\n" + store.getNumCDs() + " CD(s)");
        getListOfCDs(store);
        System.out.println("\n" + store.getNumDVDs() + " DVD(s)");
        getListOfDVDs(store);

        System.out.println("\n--------------------------------------------------------");
        System.out.println("Entire inventory value: \t\t\t$" + store.inventoryValue());
        System.out.println("--------------------------------------------------------");
    }

    /*
        getListOfBooks() Method that prints out the list of available books
        @param addProduct store The bookstore class that contains the ArrayList of books
     */
    public static void getListOfBooks(Bookstore store) {
        ArrayList<Product> books = store.getProducts();
        int i = 1;
        for (Product book : books) {
            if (book instanceof Book book1) {
                System.out.println("\t" + i + ". " + book1.getName() + " by " + book1.getCreator() + " (" + book1.getYear() + ") (" + book1.getNumCopies() + " in stock)");
                i++;
            }
        }
    }

    /*
        getListofCDs() Method that prints out the list of available CDs
        @param addProduct store The bookstore class that contains the ArrayList of CDs
     */
    public static void getListOfCDs(Bookstore store) {
        ArrayList<Product> CDs = store.getProducts();
        int i = 1;
        for (Product cd : CDs) {
            if (cd instanceof CD cd1) {
                System.out.println("\t" + i + ". " + cd1.getName() + " by " + cd1.getCreator() + " (" + cd1.getYear() + ") (" + cd1.getNumCopies() + " in stock)");
                i++;
            }
        }
    }

    /*
        getListOfDVDs() Method that prints out the list of available DVDs
        @param addProduct store The bookstore class that contains the ArrayList of DVDs
     */
    public static void getListOfDVDs(Bookstore store) {
        ArrayList<Product> DVDs = store.getProducts();
        int i = 1;
        for (Product dvd : DVDs) {
            if (dvd instanceof DVD dvd1) {
                System.out.println("\t" + i + ". " + dvd1.getName() + " (" + dvd1.getYear() + ") (" + dvd1.getNumCopies() + " in stock)");
                i++;
            }
        }
    }

    /*
        manageInventory Method that displays inventory management options like adding a new item, or displaying entire inventory and makes them work
        @param addProduct store The bookstore class that contains the ArrayLists of books, CDs, and DVDs
     */
    public static void manageInventory(Bookstore store) {
        //menu options
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t1. View Inventory\n\t2. Add New Book\n\t3. Add New CD\n\t4. Add New DVD\n\t5. Restock Products");
        System.out.print("Enter a number 1-5: ");
        int inventoryChoice = sc.nextInt();
        //validate user response
        while (inventoryChoice < 1 || inventoryChoice > 5) {
            System.out.println("\nSorry, but you have to enter a number between 1-5");
            System.out.println("\nWhat would you like to do?");
            System.out.println("\t1. View Inventory\n\t2. Add New Book\n\t3. Add New CD\n\t4. Add New DVD\n\t5. Restock Products");
            System.out.print("Enter a number 1-5: ");
            inventoryChoice = sc.nextInt();
        }

        switch (inventoryChoice) {

            //display inventory
            case 1 -> //calling the store.displayInventory() method to display current inventory
                    displayInventory(store);

            //add a new book
            case 2 -> {
                sc.nextLine(); //clear scanner buffer
                System.out.print("What's the name of the book? ");
                String bookName = sc.nextLine(); //to hold name of new book
                System.out.print("What's the name of the author? ");
                String authorName = sc.nextLine(); //to hold name of author of new book
                System.out.print("What year was it published? ");
                int bookYear = sc.nextInt(); //to hold year published of book
                //validate year published to make it 2022 or older only
                while (bookYear > 2022) {
                    System.out.println("The published year cannot be in the future");
                    System.out.print("What year was it published? ");
                    bookYear = sc.nextInt();
                }
                System.out.print("How many copies of this book are available right now? ");
                int bookCopies = sc.nextInt(); //to hold number of copies of the book available
                //validate number of copies to make it 1 or more
                while (bookCopies < 1) {
                    System.out.println("You cannot add 0 or less copies");
                    System.out.print("How many copies of this book are available right now? ");
                    bookCopies = sc.nextInt();
                }
                //adding the new book to the books ArrayList
                store.addProduct(bookName, store.getBookPrice(), bookCopies, authorName, bookYear, 1);
            }

            //add a new CD
            case 3 -> {
                sc.nextLine(); //clear scanner buffer
                System.out.print("What's the name of the CD? ");
                String CDname = sc.nextLine(); //to hold name of new CD
                System.out.print("What's the name of the producer? ");
                String producerName = sc.nextLine(); //to hold producer of the CD's name
                System.out.print("What year was it published? ");
                int cdYear = sc.nextInt(); //to hold publication year of the CD
                //validate publication year
                while (cdYear > 2022) {
                    System.out.println("The published year cannot be in the future");
                    System.out.print("What year was it published? ");
                    cdYear = sc.nextInt();
                }
                System.out.print("How many copies of this CD are available right now? ");
                int cdCopies = sc.nextInt(); //to hold number of CD copies
                //validate number of copies
                while (cdCopies < 1) {
                    System.out.println("You cannot add 0 or less copies");
                    System.out.print("How many copies of this CD are available right now? ");
                    cdCopies = sc.nextInt();
                }
                //adding the new CD to the CDs ArrayList
                store.addProduct(CDname, store.getCDprice(), cdCopies, producerName, cdYear, 2);
            }

            //add a new DVD
            case 4 -> {
                sc.nextLine(); //to clear scanner buffer
                System.out.print("What's the name of the DVD? ");
                String DVDname = sc.nextLine(); //to hold the new DVD's name
                System.out.print("What's the name of the director? ");
                String directorName = sc.nextLine(); //to hold the new DVD's director's name
                System.out.print("What year was it published? ");
                int dvdYear = sc.nextInt(); //to hold the year it was published
                //validate year of publication
                while (dvdYear > 2022) {
                    System.out.println("The published year cannot be in the future");
                    System.out.print("What year was it published? ");
                    dvdYear = sc.nextInt();
                }
                System.out.print("How many copies of this DVD are available right now? ");
                int dvdCopies = sc.nextInt(); //to hold number of copies of new DVD
                //validate number of copies
                while (dvdCopies < 1) {
                    System.out.println("You cannot add 0 or less copies");
                    System.out.print("How many copies of this DVD are available right now? ");
                    dvdCopies = sc.nextInt();
                }
                //adding new DVD to the DVDs ArrayList
                store.addProduct(DVDname, store.getDVDprice(), dvdCopies, directorName, dvdYear, 3);
            }

            //restock products
            case 5 -> {
                ArrayList<Product> temp = store.getProducts();
                int i = 1;
                System.out.println("\nChoose a product to restock:");
                for (Product p : temp) {
                    switch (p) {
                        case Book book ->
                                System.out.println("\t" + i + ". Book: " + book.getName() + " by " + book.getCreator() + " (" + book.getYear() + ") (" + book.getNumCopies() + " in stock)");
                        case CD cd ->
                                System.out.println("\t" + i + ". CD: " + cd.getName() + " by " + cd.getCreator() + " (" + cd.getYear() + ") (" + cd.getNumCopies() + " in stock)");
                        case DVD dvd ->
                                System.out.println("\t" + i + ". DVD: " + dvd.getName() + " (" + dvd.getYear() + ") (" + dvd.getNumCopies() + " in stock)");

                        default -> throw new IllegalStateException("Unexpected value: " + p);
                    }
                    i++;
                }
                System.out.print("Enter a number: ");
                int choice = sc.nextInt();
                while (choice < 1 || choice > temp.size()) {
                    System.out.print("Please enter a valid number: ");
                    choice = sc.nextInt();
                }
                System.out.print("How many copies would you like to restock? ");
                int copies = sc.nextInt();
                while (copies < 0) {
                    System.out.println("You cannot remove a copy without buying it.");
                    System.out.print("How many copies would you like to restock? ");
                    copies = sc.nextInt();
                }
                store.restockProduct(choice, copies);
            }
            default -> {
            }
        }
    }

    /*
        manageAccounts() Method that displays different options to manage accounts such as delete an account, or viewing each account's details
        @param addProduct store The bookstore class that contains the ArrayLists of members and premium members
     */
    public static void manageAccounts(Bookstore store, ArrayList<Member> memberList) {

        if (memberList.size() >= 1) {
            //menu options
            System.out.println("\nWhat would you like to do? ");
            System.out.println("\t1. Display regular member accounts\n\t2. Display premium member accounts\n\t3. Delete an account");
            System.out.print("Enter a number 1-3: ");
            int choice = sc.nextInt(); //to hold user choice
            //validate user response
            while (choice < 1 || choice > 3) {
                System.out.println("\nSorry, but you have to enter a number between 1-3 only");
                System.out.println("\nWhat would you like to do? ");
                System.out.println("\t1. Display regular member accounts\n\t2. Display premium member accounts\n\t3. Delete an account");
                System.out.print("Enter a number 1-3: ");
                choice = sc.nextInt();
            }

            switch (choice) {

                //display member list and stats
                case 1 -> {
                    if (store.getNumMembers() > 0) {
                        int i = 1;
                        //for loop that loops through the memberList ArrayList and prints out each member's name and stats
                        for (Member member : memberList) {
                            if (!(member instanceof PremiumMember)) {
                                System.out.printf("\n\t" + i + ". " + member.getFirstName() + " " + member.getLastName() + ": spent $%,.2f" + " since account creation\n", member.getMoneySpent());
                                i++;
                            }
                        }
                    } else {
                        System.out.println("\nSorry, but there are currently no regular bookstore members");
                    }
                }

                //display premium member list and stats
                case 2 -> {
                    if (memberList.size() >= 1) {
                        int k = 1;
                        //for loop that loops through the premiumMemberList ArrayList and prints out each premium member's name and stats
                        for (Member premiumMember : memberList) {
                            if (premiumMember instanceof PremiumMember premiumMember1) {
                                boolean paid = premiumMember1.getMembershipPaid(); //to get whether the premium member has paid his membership fee or not
                                //if membership is paid
                                if (paid == true) {
                                    System.out.printf("\n\t" + k + ". " + premiumMember.getFirstName() + " " + premiumMember.getLastName() + ": spent $%,.2f" + " since account creation, has " + premiumMember1.getPaymentMethod() + " set as preferred payment method, membership fee paid.\n", premiumMember.getMoneySpent());
                                    k++;
                                } //otherwise, if it's not paid
                                else {
                                    System.out.printf("\n\t" + k + ". " + premiumMember.getFirstName() + " " + premiumMember.getLastName() + ": spent $%,.2f" + " since account creation, has " + premiumMember1.getPaymentMethod() + " set as preferred payment method, membership fee past due!\n", premiumMember.getMoneySpent());
                                    k++;
                                }
                            }
                        }
                    } else {
                        System.out.println("\nSorry,but there are currently no premium bookstore members");
                    }
                }

                //delete account
                case 3 -> {
                    System.out.println("\nWhich account would you like to delete?");
                    int j = 1; //to correctly number member list

                    //for loop that loops through the memberList ArrayList and prints out each member's name
                    for (Member member : memberList) {
                        System.out.println("\t" + j + ". " + member.getFirstName() + " " + member.getLastName());
                        j++;
                    }
                    System.out.print("Enter a number: ");
                    int deleteAccount = sc.nextInt(); //to hold user choice

                    //validate user response
                    while (deleteAccount < 1 || deleteAccount > memberList.size()) {
                        System.out.println("\nSorry, but you have to enter one of the following options only");
                        System.out.println("\nWhich account would you like to delete?");
                        j = 1;
                        for (Member member : memberList) {
                            System.out.println("\t" + j + ". " + member.getFirstName() + " " + member.getLastName());
                            j++;
                        }
                        System.out.print("Enter a number: ");
                        deleteAccount = sc.nextInt();
                    }
                    store.removeMember(deleteAccount - 1); //remove member
                }
                default -> {
                }
            }
        } else {
            System.out.println("\nSorry, but there are currently have no member accounts created");
        }
    }

    /*
        startPurchase() Method that initiates the whole purchase process, it includes but is not limited to creating a new membership
        @param Bookstore store The bookstore class that contains the ArrayLists of members, premium members, books, CDs, and DVDs
     */
    public static void startPurchase(Bookstore store, ArrayList<Member> memberList) {
        //calling the displayLoginMenu() Method to display the login menu, and let the user select which member is buying.
        int loginChoice = displayLoginMenu(store, memberList);

        //if user chose to create a new membership account
        if (loginChoice == memberList.size() + 1) {
            sc.nextLine(); //clear scanner buffer
            System.out.print("\nMember First Name: ");
            String name = sc.nextLine(); //to hold new member's first name
            System.out.print("Member Last Name: ");
            String surname = sc.nextLine(); //to hold new member's last name

            //calling the premiumMembership to ask user if they want a premium membership or not
            boolean premiumMembership = premiumMembership();
            //if they do want a premium membership, ask about payment method and add member to premiumMemberList ArrayList
            if (premiumMembership == true) {
                String payment = payMethod();
                store.addPremiumMember(name, surname, 0, 0, 0, payment, true);
            } //otherwise, add member to memberList ArrayList
            else {
                store.addMember(name, surname, 0, 0, 0);
                loginChoice = loginChoice - store.getNumPremiumMembers();
            }
        }

        boolean more = true; //whether the same member would want to add more items to buy or not

        while (more == true) {

            //calling the displayPurchaseMenu() Method to display the purchase menu and return user's menu choice
            int menuChoice = displayPurchaseMenu(store);

            //switch on the user's menu choice
            switch (menuChoice) {

                //Display list of books and let user buy any
                case 1 -> {
                    if (store.getNumBooks() > 0) {
                        //calling the store.startProductPurchase() method to start the book purchasing process
                        int bookChoice = getBookChoice(store);
                        if (bookChoice == -1) {
                            return;
                        }
                        store.startProductPurchase(loginChoice, bookChoice);
                        more = buyMore();
                    } //if there are no available books at the moment
                    else {
                        System.out.println("\nSorry, there are currently no books available for purchase.");
                    }
                }

                //Display list of CDs and let user buy any
                case 2 -> {
                    if (store.getNumCDs() > 0) {
                        //calling the store.startProductPurchase() method to start the CD purchasing process
                        int cdChoice = getCDchoice(store);
                        if (cdChoice == -1) {
                            return;
                        }
                        store.startProductPurchase(loginChoice, cdChoice);
                        more = buyMore();
                    } //if there are no available CDs at the moment
                    else {
                        System.out.println("\nSorry, there are currently no CDs available for purchase.");
                    }
                }

                //Display list of DVDs and let user buy any
                case 3 -> {
                    if (store.getNumDVDs() > 0) {
                        //calling the store.startProductPurchase() method to start the DVD purchasing process
                        int dvdChoice = getDVDchoice(store);
                        if (dvdChoice == -1) {
                            return;
                        }
                        store.startProductPurchase(loginChoice, dvdChoice);
                        more = buyMore();
                    } //if there are no available DVDs at the moment
                    else {
                        System.out.println("\nSorry, there are currently no DVDs available for purchase.");
                    }
                }
                default -> {
                }
            }
        }
    }
}
