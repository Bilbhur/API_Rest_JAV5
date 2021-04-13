package com.etna.project;

import com.etna.project.dao.ProductCategoryRepository;
import com.etna.project.dao.ProductRepository;
import com.etna.project.entity.Product;
import com.etna.project.entity.ProductCategory;
import com.etna.project.service.ProductCategoryService;
import com.etna.project.service.ProductService;
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
    protected ProductService productService;

    @Autowired
    protected ProductCategoryService productCategoryService;

    private String baseUrl = "/api/products/";

    @Test
    public void create() throws Exception {
        ProductCategory pc = new ProductCategory();
        pc.setCategoryName("Test category");
        productCategoryService.create(pc);


        Product product = new Product();
        product.setDescription("un produit");
        product.setCategory(pc);
        product.setSku("oaisnfoanoin");
        product.setName("un nom");
//        product.setUnitPrice();
        product.setUnitsInStock(5);
        product.setActive(true);
//        product.setImageUrl(entity.getImageUrl());
        product = productService.create(product);

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

    @Test
    public void update() throws Exception {
        Product p = productService.getOneById(1);
        p.setDescription("toto le pouet");

        this.mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(p)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTheProduct() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "1"))
                .andDo(print())
                .andExpect(status().isNoContent());
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
