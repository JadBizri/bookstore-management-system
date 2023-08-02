package com.jadb.bookstore.managementsystem.bookstore.Member.Controller;

import com.jadb.bookstore.managementsystem.bookstore.Member.Member;
import com.jadb.bookstore.managementsystem.bookstore.Member.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){this.memberService = memberService;}

    @GetMapping
    public List<Member> getMembers(){return memberService.getMembers();
    }
}
