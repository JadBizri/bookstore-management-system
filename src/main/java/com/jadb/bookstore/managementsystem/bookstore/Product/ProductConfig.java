package com.jadb.bookstore.managementsystem.bookstore.Product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            ProductRepository repository) {
        return args -> {
            Product book = new Book(
                    "Harry Potter and the Sorcerer's Stone",
                    20,
                    3,
                    "J.K. Rowling",
                    1997
            );

            Product cd = new CD(
                    "Star Wars (Soundtrack)",
                    12,
                    7,
                    "John Williams",
                    1977
            );

            Product dvd = new DVD(
                    "Jurassic World: Dominion",
                    16,
                    9,
                    "Colin Trevorrow",
                    2022
            );

            repository.saveAll(
                    List.of(book, cd, dvd)
            );
        };
    }
}
