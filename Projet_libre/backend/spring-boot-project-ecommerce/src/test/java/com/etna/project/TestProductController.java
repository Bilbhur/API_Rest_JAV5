package com.etna.project;

import com.etna.project.dao.ProductCategoryRepository;
import com.etna.project.dao.ProductRepository;
import com.etna.project.entity.Product;
import com.etna.project.entity.ProductCategory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
public class TestProductController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected ProductCategoryRepository productCategoryRepository;

    private String baseUrl = "/api/products/";

    @Test
    public void createProduct() throws Exception {
        ProductCategory pc = new ProductCategory();
        pc.setCategoryName("Test category");
        productCategoryRepository.save(pc);


        Product product = new Product();
        product.setDescription("un produit");
        product.setCategory(pc);
        product.setSku("oaisnfoanoin");
        product.setName("un nom");
//        product.setUnitPrice();
        product.setUnitsInStock(5);
        product.setActive(true);
//        product.setImageUrl(entity.getImageUrl());
        product = productRepository.save(product);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(baseUrl + product.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(product.getDescription()));
    }

    @Test
    public void getList() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(baseUrl))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOne() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(baseUrl + "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    public void testUpdate() throws Exception {
//        Product p = productRepository.findById(1L).get();
//        p.setDescription("toto le pouet");
//
//        this.mockMvc.perform(MockMvcRequestBuilders.put(baseUrl)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(asJsonString(p)))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testCreateApi() throws Exception{
//
//        Product newProduct = new Product();
//        newProduct.setCategory(productCategoryRepository.findById(1L).get());
//        newProduct.setDescription("description nouvelle");
//
//        this.mockMvc
//                .perform(
//                        MockMvcRequestBuilders.post("/api/products")
//                                .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(newProduct)))
//                .andDo(print())
//                .andExpect(status().isCreated());
//    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
