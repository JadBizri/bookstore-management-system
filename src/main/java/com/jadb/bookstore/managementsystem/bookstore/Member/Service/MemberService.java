package com.jadb.bookstore.managementsystem.bookstore.Member.Service;

import com.jadb.bookstore.managementsystem.bookstore.Member.Member;
import com.jadb.bookstore.managementsystem.bookstore.Member.Repository.MemberRepository;
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

    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

    public void addNewMember(Member m) {
        Optional<Member> memberOptional = memberRepository
                .getMemberByFName(m.getFirstName());
        if(memberOptional.isPresent())
            throw new IllegalStateException("Member exists!");
        memberRepository.save(m);
    }
}
