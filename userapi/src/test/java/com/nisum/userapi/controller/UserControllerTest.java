package com.nisum.userapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String baseUrl = "/api/user";

    private String getUserJson(String email, String password) {
        return """
            {
              "name": "Juan Rodriguez",
              "email": "%s",
              "password": "%s",
              "phones": [
                {
                  "number": "1234567",
                  "citycode": "1",
                  "countrycode": "57"
                }
              ]
            }
            """.formatted(email, password);
    }
    
    @Test
    void testSaveUser_Success() throws Exception {
        String userJson = getUserJson("juan1@nisum.cl", "@Password1");

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.created").exists())
                .andExpect(jsonPath("$.modified").exists())
                .andExpect(jsonPath("$.last_login").exists())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.isactive").exists());
    }

    @Test
    void testWhenEmailAlreadyExists_Fail() throws Exception {
        String userJson = getUserJson("juan2@nisum.cl", "@Password1");

        // Register once
        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated());

        // Try registering again
        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("The email is already registered."));
    }

    @Test
    void testUserWithInvalidPassword_Fail() throws Exception {
        String userJson = getUserJson("juan3@nisum.cl", "Password1");

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid password format."));
    }

    @Test
    void testUserWithInvalidEmail_Fail() throws Exception {
        String invalidEmailUserJson = getUserJson("juan4@nisum.com", "@Password1");

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEmailUserJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid email format."));
    }
}

