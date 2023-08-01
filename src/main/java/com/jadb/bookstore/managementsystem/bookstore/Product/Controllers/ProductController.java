package com.jadb.bookstore.managementsystem.bookstore.Product.Controllers;

import com.jadb.bookstore.managementsystem.bookstore.Product.Book;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import com.jadb.bookstore.managementsystem.bookstore.Product.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){this.productService = productService;}

    @GetMapping
    public List<Product> getProducts(){return productService.getProducts();
    }

    @PostMapping
    public void addNewBook(@RequestBody Book b){productService.addNewBook(b);}

}
