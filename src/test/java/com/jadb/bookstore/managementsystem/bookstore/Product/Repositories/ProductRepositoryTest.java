package com.jadb.bookstore.managementsystem.bookstore.Product.Repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testFindProductByName() {
        // Test data
        String productName = "Test Product";
        Product product = new Product();
        product.setId(1L);
        product.setName(productName);

        // Mocking the repository's behavior
        when(productRepositoryMock.findProductByName(productName)).thenReturn(Optional.of(product));

        // Call the service method
        Optional<Product> result = productService.findProductByName(productName);

        // Assertions
        assertTrue(result.isPresent()); // Check that the result is present
        assertEquals(product, result.get()); // Check that the returned product is the expected one
    }

    @Test
    public void testGetProductsByType() {
        // Test data
        Class<?> productType = Book.class;
        Product book1 = new Book();
        Product book2 = new Book();
        List<Product> mockProducts = Arrays.asList(book1, book2);

        // Mocking the repository's behavior
        when(productRepositoryMock.getProductsBy(productType)).thenReturn(mockProducts);

        // Call the service method
        List<Product> result = productService.getProductsByType(productType);

        // Assertions
        assertNotNull(result); // Check that the result is not null
        assertEquals(2, result.size()); // Check that the result contains the expected number of products
        assertTrue(result.contains(book1)); // Check that the result contains the mocked products
        assertTrue(result.contains(book2));
    }
}
