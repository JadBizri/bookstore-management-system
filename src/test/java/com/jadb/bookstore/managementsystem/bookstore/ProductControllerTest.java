package com.jadb.bookstore.managementsystem.bookstore;

import com.jadb.bookstore.managementsystem.bookstore.Product.Book;
import com.jadb.bookstore.managementsystem.bookstore.Product.CD;
import com.jadb.bookstore.managementsystem.bookstore.Product.DVD;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //GET tests

    @Test
    public void testGetProducts() throws Exception {
        //perform GET request
        ResultActions resultActions = mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON));

        //assert response
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void testGetProductsByTypeAndSort() throws Exception {
        //perform GET request with type and sort parameters
        ResultActions resultActions = mockMvc.perform(get("/api/v1/products/type")
                .param("type", "Books")
                .param("sort", "PriceLow")
                .contentType(MediaType.APPLICATION_JSON));

        //assert response
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testGetProductById() throws Exception {
        //perform GET request with the product ID
        long productId = 2;
        ResultActions resultActions = mockMvc.perform(get("/api/v1/products/" + productId)
                .contentType(MediaType.APPLICATION_JSON));

        //assert response
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.name").value("Star Wars (Soundtrack)"));
    }

    //PUT tests

    @Test
    public void testAddNewBook() throws Exception {
        // Create a Book object and convert it to JSON
        Book book = new Book("name", 10, 0, "author", 2002, "isbn");
        String bookJson = objectMapper.writeValueAsString(book);

        // Perform POST request with the JSON data
        ResultActions resultActions = mockMvc.perform(post("/api/v1/products/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson));

        // Assert response
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testAddNewCD() throws Exception {
        // Create a CD object and convert it to JSON
        CD cd = new CD("name", 10, 0, "author", 2002);
        String cdJson = objectMapper.writeValueAsString(cd);

        // Perform POST request with the JSON data
        ResultActions resultActions = mockMvc.perform(post("/api/v1/products/cd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cdJson));

        // Assert response
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testAddNewDVD() throws Exception {
        // Create a DVD object and convert it to JSON
        DVD dvd = new DVD("name", 10, 0, "author", 2002);
        String dvdJson = objectMapper.writeValueAsString(dvd);

        // Perform POST request with the JSON data
        ResultActions resultActions = mockMvc.perform(post("/api/v1/products/dvd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dvdJson));

        // Assert response
        resultActions.andExpect(status().isOk());
    }

    //PUT test

}
