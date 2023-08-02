package com.jadb.bookstore.managementsystem.bookstore.Member.Config;

import com.jadb.bookstore.managementsystem.bookstore.Member.Member;
import com.jadb.bookstore.managementsystem.bookstore.Member.PremiumMember;
import com.jadb.bookstore.managementsystem.bookstore.Member.Repository.MemberRepository;
import com.jadb.bookstore.managementsystem.bookstore.Product.Book;
import com.jadb.bookstore.managementsystem.bookstore.Product.CD;
import com.jadb.bookstore.managementsystem.bookstore.Product.DVD;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MemberConfig {

    @Bean
    CommandLineRunner commandLineRunner2(
            MemberRepository repository) {
        return args -> {
            Member tiffany = new Member(
                    "Tiffany",
                    "Miles",
                    3,
                    2,
                    1
            );

            PremiumMember jorge = new PremiumMember(
                    "Jorge",
                    "Jones",
                    7,
                    6,
                    5,
                    "Visa",
                    true
            );

            repository.saveAll(
                    List.of(tiffany, jorge)
            );
        };
    }
}
