package com.jadb.bookstore.managementsystem.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public void addNewBook(Book b) {
        Optional<Product> productOptional = productRepository
                .findProductByName(b.getName());
        if(productOptional.isPresent())
            throw new IllegalStateException("Book exists!");
        productRepository.save(b);
    }
}
