package com.etna.project;

import com.etna.project.dao.ProductCategoryRepository;
import com.etna.project.dao.ProductRepository;
import com.etna.project.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestProductController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected ProductCategoryRepository productCategoryRepository;

    @Test
    public void getList() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/products"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        Product newProduct = new Product();
        newProduct.setCategory(productCategoryRepository.getOne(1L));
        newProduct.setDescription("description 2");
        newProduct = productRepository.save(newProduct);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/products/" + newProduct.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(newProduct.getDescription()));
    }

    @Test
    public void testUpdate() throws Exception {
        Product p = productRepository.findById(1L).get();
        p.setDescription("toto le pouet");

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(p)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateApi() throws Exception{

        Product newProduct = new Product();
        newProduct.setCategory(productCategoryRepository.findById(1L).get());
        newProduct.setDescription("description nouvelle");

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/products")
                                .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newProduct)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
