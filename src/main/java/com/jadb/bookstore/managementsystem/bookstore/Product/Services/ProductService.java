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
            //update the existing product with data from the updatedProduct
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setYear(updatedProduct.getYear());
            existingProduct.setCreator(updatedProduct.getCreator());
            existingProduct.setNumCopies(updatedProduct.getNumCopies());
            switch (existingProduct) {
                case Book book -> {
                    book.setIsbn(((Book) updatedProduct).getIsbn());
                    Optional<Book> bookOptional = productRepository
                            .findBookByIsbn(book.getIsbn());
                    if (bookOptional.isPresent() && !Objects.equals(book.getIsbn(), bookOptional.get().getIsbn()))
                        throw new IllegalStateException("Book exists!");
                }
                case CD cd -> {
                    Optional<CD> cdOptional = productRepository.findCdByName(existingProduct.getName());
                    if (cdOptional.isPresent() && !Objects.equals(cd.getName(), cdOptional.get().getName()))
                        throw new IllegalStateException("CD exists!");
                }
                case DVD dvd -> {
                    Optional<DVD> dvdOptional = productRepository.findDvdByName(existingProduct.getName());
                    if (dvdOptional.isPresent() && !Objects.equals(dvd.getName(), dvdOptional.get().getName()))
                        throw new IllegalStateException("DVD exists!");
                }
                default -> throw new IllegalStateException("Unexpected Error - No Type Found");

            }
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
