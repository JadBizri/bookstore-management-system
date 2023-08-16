package com.jadb.bookstore.managementsystem.bookstore.Product.Configs;

import com.jadb.bookstore.managementsystem.bookstore.Product.Book;
import com.jadb.bookstore.managementsystem.bookstore.Product.CD;
import com.jadb.bookstore.managementsystem.bookstore.Product.DVD;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import com.jadb.bookstore.managementsystem.bookstore.Product.Repositories.ProductRepository;
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
                    19.99,
                    10,
                    "J.K. Rowling",
                    1997,
                    "978-0590353403"
            );

            Product book1 = new Book(
                    "Harry Potter and the Chamber of Secrets",
                    19.99,
                    10,
                    "J.K. Rowling",
                    1998,
                    "978-0439064873"
            );

            Product book2 = new Book(
                    "Harry Potter and the Prisoner of Azkaban",
                    19.99,
                    8,
                    "J.K. Rowling",
                    1999,
                    "978-0439136358"
            );

            Product cd = new CD(
                    "Star Wars (Soundtrack)",
                    7.99,
                    9,
                    "John Williams",
                    1977
            );

            Product cd1 = new CD(
                    "Star Wars: The Force Awakens (Album)",
                    7.99,
                    7,
                    "John Williams",
                    2015
            );

            Product cd2 = new CD(
                    "Thriller (Album)",
                    13.99,
                    3,
                    "Michael Jackson",
                    1982
            );

            Product dvd = new DVD(
                    "Jurassic World: Dominion",
                    14.99,
                    12,
                    "Colin Trevorrow",
                    2022
            );

            Product dvd1 = new DVD(
                    "Top Gun: Maverick",
                    16.99,
                    4,
                    "Joseph Kosinski",
                    2022
            );

            Product dvd2 = new DVD(
                    "Minions: The Rise of Gru",
                    15.99,
                    8,
                    "Kyle Balda",
                    2022
            );

            repository.saveAll(
                    List.of(book, book1, book2, cd, cd1, cd2, dvd, dvd1, dvd2)
            );
        };
    }
}
