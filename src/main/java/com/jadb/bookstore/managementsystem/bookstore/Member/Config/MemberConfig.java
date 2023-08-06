package com.jadb.bookstore.managementsystem.bookstore.Member.Config;

import com.jadb.bookstore.managementsystem.bookstore.Member.Member;
import com.jadb.bookstore.managementsystem.bookstore.Member.PremiumMember;
import com.jadb.bookstore.managementsystem.bookstore.Member.Repository.MemberRepository;
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
                    1,
                    "t.miles@gmail.com"
            );

            PremiumMember jorge = new PremiumMember(
                    "Jorge",
                    "Jones",
                    7,
                    6,
                    5,
                    "Visa",
                    true,
                    "jorge.j@gmail.com"
            );

            repository.saveAll(
                    List.of(tiffany, jorge)
            );
        };
    }
}
