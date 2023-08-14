package com.jadb.bookstore.managementsystem.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

}
