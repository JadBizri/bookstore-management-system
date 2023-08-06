package com.jadb.bookstore.managementsystem.bookstore.Member.Controller;

import com.jadb.bookstore.managementsystem.bookstore.Member.Member;
import com.jadb.bookstore.managementsystem.bookstore.Member.Repository.MemberRepository;
import com.jadb.bookstore.managementsystem.bookstore.Member.Service.MemberService;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    //GET Member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void addNewMember(@RequestBody Member m) {
        memberService.addNewMember(m);
    }
}
