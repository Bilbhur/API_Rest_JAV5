package com.etna.project;

import com.etna.project.entity.ProductCategory;
import com.etna.project.service.ProductCategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestProductCategoryController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ProductCategoryService productCategoryService;

    private String baseUrl = "/api/product-category/";

    @Before
    public void init() {
        ProductCategory pc = new ProductCategory();
        pc.setCategoryName("Test category 1");
        productCategoryService.create(pc);

        pc = new ProductCategory();
        pc.setCategoryName("Test category 2");
        productCategoryService.create(pc);

        pc = new ProductCategory();
        pc.setCategoryName("Test category 3");
        productCategoryService.create(pc);
    }

    @After
    public void clear() {

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
                .perform(MockMvcRequestBuilders.delete(baseUrl + "3"))
                .andDo(print())
                .andExpect(status().isNoContent());

        getList();
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
