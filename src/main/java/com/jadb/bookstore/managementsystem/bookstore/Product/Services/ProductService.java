package com.jadb.bookstore.managementsystem.bookstore.Product.Services;

import com.jadb.bookstore.managementsystem.bookstore.Product.Book;
import com.jadb.bookstore.managementsystem.bookstore.Product.CD;
import com.jadb.bookstore.managementsystem.bookstore.Product.DVD;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import com.jadb.bookstore.managementsystem.bookstore.Product.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addNewBook(Book b) {
        Optional<Book> bookOptional = productRepository
                .findBookByIsbn(b.getIsbn());
        if (bookOptional.isPresent())
            throw new IllegalStateException("Book exists!");
        productRepository.save(b);
    }

    public void addNewCD(CD cd) {
        Optional<CD> cdOptional = productRepository.findCdByName(cd.getName());
        if (cdOptional.isPresent())
            throw new IllegalStateException("CD exists!");
        productRepository.save(cd);
    }

    public void addNewDVD(DVD dvd) {
        Optional<DVD> dvdOptional = productRepository.findDvdByName(dvd.getName());
        if (dvdOptional.isPresent())
            throw new IllegalStateException("DVD exists!");
        productRepository.save(dvd);
    }

    public Product updateProduct(long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct != null) {
            if (existingProduct instanceof Book existingBook) {
                Book updatedBook = (Book) updatedProduct;
                Optional<Book> bookOptional = productRepository
                        .findBookByIsbn(updatedBook.getIsbn());
                if (bookOptional.isPresent() && !existingBook.getIsbn().equals(updatedBook.getIsbn()))
                    throw new IllegalStateException("Book with same ISBN already exists!");
                ((Book) existingProduct).setIsbn(((Book) updatedProduct).getIsbn());
            } else if (existingProduct instanceof CD) {
                Optional<CD> cdOptional = productRepository.findCdByName(updatedProduct.getName());
                if (cdOptional.isPresent() && !existingProduct.getName().equals(updatedProduct.getName()))
                    throw new IllegalStateException("CD already exists!");
            } else if (existingProduct instanceof DVD) {
                Optional<DVD> dvdOptional = productRepository.findDvdByName(updatedProduct.getName());
                if (dvdOptional.isPresent() && !existingProduct.getName().equals(updatedProduct.getName()))
                    throw new IllegalStateException("DVD already exists!");
            } else throw new IllegalStateException("Unexpected Error - No Type Found");

            //update the existing product with data from the updatedProduct
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setYear(updatedProduct.getYear());
            existingProduct.setCreator(updatedProduct.getCreator());
            existingProduct.setNumCopies(updatedProduct.getNumCopies());

            //save the updated product in the database
            return productRepository.save(existingProduct);
        }
        //if product with the given ID does not exist, return null or throw an exception
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
