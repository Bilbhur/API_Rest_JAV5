package com.etna.project;

import com.etna.project.entity.ProductCategory;
import com.etna.project.service.ProductCategoryService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
public class TestProductCategoryController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ProductCategoryService productCategoryService;

    private String baseUrl = "/api/product-category/";

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
    public void create() throws Exception {
        ProductCategory pc = new ProductCategory();
        pc.setCategoryName("Test category");

        this.mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(pc)))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void deleteProductCategory() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete(baseUrl + "1"))
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
