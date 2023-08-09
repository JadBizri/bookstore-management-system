package com.jadb.bookstore.managementsystem.bookstore.Product.Controllers;

import com.jadb.bookstore.managementsystem.bookstore.Product.Book;
import com.jadb.bookstore.managementsystem.bookstore.Product.CD;
import com.jadb.bookstore.managementsystem.bookstore.Product.DVD;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import com.jadb.bookstore.managementsystem.bookstore.Product.Repositories.ProductRepository;
import com.jadb.bookstore.managementsystem.bookstore.Product.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    //GET List of all products
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    //GET List of products, optionally by type or sorted
    @GetMapping(value = { "/type", "/sort" })
    public ResponseEntity<List<Product>> getProductsByTypeAndSort(
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "sort", required = false) String sort) {

        List<Product> products;

        if (!Objects.equals(type, "All")) {
            // Handle filtering by type
            Class<?> productType;
            switch (type) {
                case "Books" -> productType = Book.class;
                case "CDs" -> productType = CD.class;
                case "DVDs" -> productType = DVD.class;
                default -> {
                    return ResponseEntity.badRequest().build(); // Invalid type
                }
            }
            products = productRepository.getProductsByType(productType);
        } else {
            // Return all products if type parameter is not provided ("All" is selected)
            products = productRepository.findAll();
        }

        System.out.println("HEREEEE --------> " + products.toString());

        if (!Objects.equals(sort, "Default")) {
            // Handle sorting
            if (sort.equals("PriceLow")) {
                products.sort(Comparator.comparing(Product::getPrice));
            } else if (sort.equals("PriceHigh")) {
                products.sort(Comparator.comparing(Product::getPrice).reversed());
            } // Add more sorting options as needed
        }
        System.out.println("HEREEEE --------> " + products.toString());
        return ResponseEntity.ok(products);
    }

    //GET Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //POST create new Book
    @PostMapping("/book")
    public void addNewBook(@RequestBody Book b) {
        productService.addNewBook(b);
    }

    //POST create new CD
    @PostMapping("/cd")
    public void addNewCD(@RequestBody CD cd) {
        productService.addNewCD(cd);
    }

    //POST create new DVD
    @PostMapping("/dvd")
    public void addNewDVD(@RequestBody DVD dvd) {
        productService.addNewDVD(dvd);
    }

    // PUT - Update a Product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product updatedProduct) {
        Product updatedProductResult = productService.updateProduct(id, updatedProduct);

        if (updatedProductResult != null) {
            return ResponseEntity.ok(updatedProductResult);
        } else {
            // If product with the given ID does not exist, return a 404 Not Found status.
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Delete a Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);

        // Return a 204 No Content response to indicate successful deletion
        return ResponseEntity.noContent().build();
    }
}
