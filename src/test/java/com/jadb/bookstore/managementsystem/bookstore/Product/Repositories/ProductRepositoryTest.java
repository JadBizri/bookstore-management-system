package com.jadb.bookstore.managementsystem.bookstore.Product.Repositories;

import com.jadb.bookstore.managementsystem.bookstore.Product.Book;
import com.jadb.bookstore.managementsystem.bookstore.Product.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testFindProductByName() {
        // Test data
        String productName = "Test Product";
        Book book = new Book();
        book.setName(productName);

        // Mocking the repository's behavior
        when(productRepository.findProductByName(productName)).thenReturn(Optional.of(book));

        // Call the service method
        Optional<Product> result = productRepository.findProductByName(productName);

        // Assertions
        assertEquals(book, result.orElse(null)); // Check that the returned product is the expected one
    }

    @Test
    public void testGetProductsByType() {
        // Test data
        Class<?> productType = Book.class;
        Book book1 = new Book();
        Book book2 = new Book();
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(book1);
        mockProducts.add(book2);

        // Mocking the repository's behavior
        when(productRepository.getProductsByType(productType)).thenReturn(mockProducts);

        // Call the service method
        List<Product> result = productRepository.getProductsByType(productType);

        // Assertions
        assertEquals(2, result.size()); // Check that the result contains the expected number of products
        assertEquals(book1, result.get(0)); // Check that the result contains the mocked products
        assertEquals(book2, result.get(1));
    }
}
