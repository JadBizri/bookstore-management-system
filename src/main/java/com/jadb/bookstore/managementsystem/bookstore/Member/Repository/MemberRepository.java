package com.jadb.bookstore.managementsystem.bookstore.Member.Repository;

import com.jadb.bookstore.managementsystem.bookstore.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
