package com.jadb.bookstore.managementsystem.bookstore.Product.Repositories;

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

    @Query("SELECT p FROM Product p WHERE TYPE(p) = :type")
    List<Product> getProductsByType(@Param("type") Class<?> type);
}
