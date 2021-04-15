package com.etna.project;

import com.etna.project.entity.JwtUserDetails;
import com.etna.project.entity.User;
import com.etna.project.entity.UserDetails;
import com.etna.project.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestUserController extends SpringBootProjectEcommerceApplicationTests{

    @Autowired
    protected MockMvc mockMvc;

    protected String token;

    @Autowired
    private UserService userService;

    @Before
    public void init() {
        User user = new User();
        user.setUsername("toto");
        user.setPassword("pouet");
        userService.create(user);
    }

    @Test
    public void register() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("pouet");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void authenticate() throws Exception {
        User user = new User();
        user.setUsername("toto");
        user.setPassword("pouet");

        String body = asJsonString(user);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
                .andExpect(status().isOk());
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
