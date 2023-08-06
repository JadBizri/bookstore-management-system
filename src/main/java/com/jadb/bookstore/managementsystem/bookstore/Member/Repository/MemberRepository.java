package com.jadb.bookstore.managementsystem.bookstore.Member.Repository;

import com.jadb.bookstore.managementsystem.bookstore.Member.Member;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.fName = ?1")
    public Optional<Member> getMemberByFName(String fName);

    @Query("SELECT m FROM Member m WHERE m.email = ?1")
    public Optional<Member> getMemberByEmail(String email);

    @Query("SELECT m FROM Member m WHERE TYPE(m) = :type")
    List<Member> getMembersByType(@Param("type") Class<?> type);
}
