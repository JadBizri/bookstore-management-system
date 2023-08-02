package com.jadb.bookstore.managementsystem.bookstore.Member.Repository;

import com.jadb.bookstore.managementsystem.bookstore.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.fName = ?1")
    public Optional<Member> getMemberByFName(String fName);

}
