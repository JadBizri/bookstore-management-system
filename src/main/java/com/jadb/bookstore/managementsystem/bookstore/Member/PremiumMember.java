package com.jadb.bookstore.managementsystem.bookstore.Member;

import jakarta.persistence.Entity;

@Entity
public class PremiumMember extends Member {

    private boolean feePaid; //to hold whether the monthly premium membership fee is paid or not
    private String payment; //to hold the premium member's payment method

    /*
        Constructor that accepts arguments for the PremiumMember's name, PremiumMembership status, whether the membership fee is paid or not and number of books, CDs, and DVDs bought by that PremiumMember
        @param String fName The first name of the PremiumMember
        @param String lName The last name of the PremiumMember
        @param int booksBought The number of books bought by the PremiumMember
        @param int CDsBought The number of CDs bought by the PremiumMember
        @param int DVDsBought The number of DVDs bought by the PremiumMember
        @param String payment The preferred payment method of the premium member
        @param boolean feePaid Whether the monthly membership fee has been paid or not
    */
    public PremiumMember(long id, String fName, String lName, int booksBought, int CDsBought, int DVDsBought, String payment, boolean feePaid, String email){
        super(id, fName, lName, booksBought, CDsBought, DVDsBought, email);
        this.payment = payment;
        this.feePaid = feePaid;
    }

    public PremiumMember(String fName, String lName, int booksBought, int CDsBought, int DVDsBought, String payment, boolean feePaid, String email){
        super(fName, lName, booksBought, CDsBought, DVDsBought, email);
        this.payment = payment;
        this.feePaid = feePaid;
    }

    public PremiumMember() {

    }

    /*
        getPaymentMethod() Method that returns the payment method of the premium member
        @return String The payment method of the premium member
    */
    public String getPaymentMethod(){
        return payment;
    }

    /*
        setPaymentMethod() Method that sets the payment method used by the PremiumMember
        @param String payment The payment method used by the premium member
    */
    public void setPaymentMethod(String payment){
        this.payment = payment;
    }

    /*
        getMembershipPaid() Method that returns whether the premium member has paid the membership fee or not
        @return boolean Whether the membership fee has been paid or not
    */
    public boolean getMembershipPaid(){
        return feePaid;
    }

    /*
        setMembershipPaid() Method that sets whether the premium member has paid the membership fee or not
        @param boolean paid Whether the membership fee has been paid or not
    */
    public void setMembershipPaid(boolean feePaid){
        this.feePaid = feePaid;
    }
}