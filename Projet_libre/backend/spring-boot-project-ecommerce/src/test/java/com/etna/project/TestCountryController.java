package com.etna.project;

import com.etna.project.entity.Country;
import com.etna.project.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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
public class TestCountryController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected CountryService countryService;

    private String baseUrl = "/api/countries/";

    @Before
    public void init() {
        Country country = new Country();
        country.setCode("PQ");
        country.setName("Pourquoi");
        countryService.create(country);

        country = new Country();
        country.setCode("FR");
        country.setName("Fraance");
        countryService.create(country);

        country = new Country();
        country.setCode("BT");
        country.setName("Blurth");
        countryService.create(country);
    }

    @Test
    public void createCountry() throws Exception {
        Country country = new Country();
        country.setCode("EW");
        country.setName("Test");

        this.mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(country)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(baseUrl))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneCountry() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneCountryNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+ "99999"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateCountry() throws Exception {
        int idCountry = 1;
        Country country = countryService.getOneById(idCountry);
        country.setName("Ceci est un update");

        this.mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + idCountry)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(country)))
                .andDo(print())
                .andExpect(status().isOk());
        this.getList();
    }

    @Test
    public void deleteCountry() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete(baseUrl + "3"))
                .andDo(print())
                .andExpect(status().isNoContent());
        this.getList();
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
