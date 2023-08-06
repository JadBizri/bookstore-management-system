package com.jadb.bookstore.managementsystem.bookstore.Member.Service;

import com.jadb.bookstore.managementsystem.bookstore.Member.Member;
import com.jadb.bookstore.managementsystem.bookstore.Member.PremiumMember;
import com.jadb.bookstore.managementsystem.bookstore.Member.Repository.MemberRepository;
import com.jadb.bookstore.managementsystem.bookstore.Product.Book;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public void addNewMember(Member m) {
        Optional<Member> memberOptional = memberRepository
                .getMemberByEmail(m.getEmail());
        if (memberOptional.isPresent())
            throw new IllegalStateException("Member exists!");
        memberRepository.save(m);
    }

    public Member updateProduct(long id, Member updatedMember) {
        Member existingMember = memberRepository.findById(id).orElse(null);

        if (existingMember != null) {
            // Update the existing product with data from the updatedProduct
            existingMember.setFirstName(updatedMember.getFirstName());
            existingMember.setLastName(updatedMember.getLastName());
            existingMember.setNumOfBooksBought(updatedMember.getNumOfBooksBought());
            existingMember.setNumOfCDsBought(updatedMember.getNumOfCDsBought());
            existingMember.setNumOfDVDsBought(updatedMember.getNumOfDVDsBought());
            existingMember.setEmail(updatedMember.getEmail());
            if (existingMember instanceof PremiumMember) {
                ((PremiumMember) existingMember).setPaymentMethod(((PremiumMember) updatedMember).getPaymentMethod());
                ((PremiumMember) existingMember).setMembershipPaid(((PremiumMember) updatedMember).getMembershipPaid());
            }

            // Save the updated member in the database
            return memberRepository.save(existingMember);
        }
        // If member with the given ID does not exist, throw an exception
        throw new IllegalStateException("Member with ID " + id + " was not found");
    }
}
