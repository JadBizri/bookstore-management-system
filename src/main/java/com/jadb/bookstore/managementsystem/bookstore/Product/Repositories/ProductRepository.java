package com.jadb.bookstore.managementsystem.bookstore.Product.Repositories;

import com.jadb.bookstore.managementsystem.bookstore.Product.Book;
import com.jadb.bookstore.managementsystem.bookstore.Product.CD;
import com.jadb.bookstore.managementsystem.bookstore.Product.DVD;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Optional<Product> findProductByName(String name);

    @Query("SELECT cd FROM CD cd WHERE cd.name = ?1")
    Optional<CD> findCdByName(String name);

    @Query("SELECT dvd FROM DVD dvd WHERE dvd.name = ?1")
    Optional<DVD> findDvdByName(String name);

    @Query("SELECT b FROM Book b WHERE b.isbn = ?1")
    Optional<Book> findBookByIsbn(String isbn);

    @Query("SELECT p FROM Product p WHERE TYPE(p) = :type")
    List<Product> getProductsByType(@Param("type") Class<?> type);

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> getProductsByPriceLowToHigh();

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> getProductsByPriceHighToLow();
}
