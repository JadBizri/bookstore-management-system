package com.jadb.bookstore.managementsystem.bookstore.Member;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type", discriminatorType = DiscriminatorType.STRING)
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //to hold member id
    private String fName; //to hold member's first name
    private String lName; //to hold member's last name
    private int booksBought; //to hold the number of books bought by a customer
    private int CDsBought; //to hold the number of CDs bought by a customer
    private int DVDsBought; //to hold the number of DVDs bought by a customer

    /*
        Constructor that accepts arguments for the member's fName, membership status, and number of booksBought, CDsBought, and DVDsBought bought by that member
        @param String fName The first name of the member
        @param String lName The last name of the member
        @param int booksBought The number of books bought by the member
        @param int CDsBought The number of CDs bought by the member
        @param int DVDsBought The number of DVDs bought by the member
    */
    public Member(long id, String fName, String lName, int booksBought, int CDsBought, int DVDsBought){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.booksBought = booksBought;
        this.CDsBought = CDsBought;
        this.DVDsBought = DVDsBought;
    }

    public Member(String fName, String lName, int booksBought, int CDsBought, int DVDsBought) {
        this.fName = fName;
        this.lName = lName;
        this.booksBought = booksBought;
        this.CDsBought = CDsBought;
        this.DVDsBought = DVDsBought;
    }

    public Member() {

    }

    /*
            getId() Method that returns a member's id
            @return long The member's id
        */
    public long getId() {
        return id;
    }

    /*
        setId() Method that sets a member's id
        @param long The id of the member to be set in the account
    */
    public void setId(long id) {
        this.id = id;
    }

    /*
        getFirstName() Method that returns a member's first name
        @return String The member's first name
    */
    public String getFirstName(){
        return fName;
    }

    /*
        setFirstName() Method that sets a member's first name
        @param String fName The first name of the member to be set in the account
    */
    public void setFirstName(String fName){
        this.fName = fName;
    }

    /*
        getLastName() Method that returns a member's last name
        @return String The member's last name
    */
    public String getLastName(){
        return lName;
    }

    /*
        setLastName() Method that sets a member's last name
        @param String lName The last name of the member to be set in the account
    */
    public void setLastName(String lName){
        this.lName = lName;
    }

    /*
        getNumOfBooksBought() Method that returns the number of books bought by the member
        @return int The number of books bought by member
    */
    public int getNumOfBooksBought(){
        return booksBought;
    }

    /*
        setNumOfBooksBought() Method that sets the number of books bought by the member
        @param int booksBought The number of books the member has bought
    */
    public void setNumOfBooksBought(int booksBought){
        this.booksBought = booksBought;
    }

    /*
        getNumOfCDsBought() Method that returns the number of CDs bought by the member
        @return int The number of CDs bought by member
    */
    public int getNumOfCDsBought(){
        return CDsBought;
    }

    /*
        setNumOfCDsBought() Method that sets the number of CDs bought by the member
        @param int CDsBought The number of CDs bought by the member
    */
    public void setNumOfCDsBought(int CDsBought){
        this.CDsBought = CDsBought;
    }

    /*
        getNumOfDVDsBought() Method that returns the number of DVDs bought by the member
        @return int The number of DVDs bought by member
    */
    public int getNumOfDVDsBought(){
        return DVDsBought;
    }

    /*
        setNumOfDVDsBought() Method that sets the number of DVDs bought by the member
        @param int DVDsBought The number of DVDs bought by the member
    */
    public void setNumOfDVDsBought(int DVDsBought){
        this.DVDsBought = DVDsBought;
    }

    @Override
    public String toString() {
        return "ID " + id + ": " + lName + ", " + fName;
    }
}