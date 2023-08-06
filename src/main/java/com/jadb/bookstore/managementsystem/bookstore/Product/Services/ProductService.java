package com.jadb.bookstore.managementsystem.bookstore.Product.Services;

import com.jadb.bookstore.managementsystem.bookstore.Product.Book;
import com.jadb.bookstore.managementsystem.bookstore.Product.CD;
import com.jadb.bookstore.managementsystem.bookstore.Product.DVD;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import com.jadb.bookstore.managementsystem.bookstore.Product.Repositories.ProductRepository;
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
        Optional<Book> bookOptional = productRepository
                .findBookByIsbn(b.getIsbn());
        if(bookOptional.isPresent())
            throw new IllegalStateException("Book exists!");
        productRepository.save(b);
    }

    public void addNewCD(CD cd) {
        Optional<Product> cdOptional = productRepository.findProductByName(cd.getName());
        if(cdOptional.isPresent())
            throw new IllegalStateException("CD exists!");
        productRepository.save(cd);
    }

    public void addNewDVD(DVD dvd) {
        Optional<Product> dvdOptional = productRepository.findProductByName(dvd.getName());
        if(dvdOptional.isPresent())
            throw new IllegalStateException("DVD exists!");
        productRepository.save(dvd);
    }

    public Product updateProduct(long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct != null) {
            // Update the existing product with data from the updatedProduct
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setYear(updatedProduct.getYear());
            existingProduct.setCreator(updatedProduct.getCreator());
            existingProduct.setNumCopies(updatedProduct.getNumCopies());
            if(existingProduct instanceof Book){
                ((Book) existingProduct).setIsbn(((Book) updatedProduct).getIsbn());
            }

            // Save the updated product in the database
            return productRepository.save(existingProduct);
        }

        // If product with the given ID does not exist, return null or throw an exception
        throw new IllegalStateException("Product with ID " + id + " was not found");
    }

    public void deleteProductById(long id) {
        // Check if the product with the given ID exists
        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
            // If the product does not exist, throw exception
            throw new IllegalStateException("Could not find Product with ID " + id);
        }
        productRepository.delete(existingProduct);
    }
}
