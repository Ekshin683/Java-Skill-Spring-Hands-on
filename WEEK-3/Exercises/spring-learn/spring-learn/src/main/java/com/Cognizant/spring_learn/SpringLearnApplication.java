package com.cognizant.springlearn;

import com.cognizant.springlearn.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc   // configures MockMvc automatically
class SpringLearnApplicationTests {

    // Autowire the controller to test it is loaded by Spring
    @Autowired
    private CountryController countryController;

    // MockMvc simulates HTTP requests without starting a real server
    @Autowired
    private MockMvc mvc;

    // Test 1: verify CountryController is loaded by Spring context
    @Test
    public void contextLoads() {
        assertNotNull(countryController);
    }

    // Test 2: GET /country → returns India with code=IN, name=India
    @Test
    public void testGetCountry() throws Exception {
        ResultActions actions = mvc.perform(get("/country"));

        // Check HTTP status is 200 OK
        actions.andExpect(status().isOk());

        // Check "code" field exists in JSON response
        actions.andExpect(jsonPath("$.code").exists());

        // Check value of "code" is "IN"
        actions.andExpect(jsonPath("$.code").value("IN"));

        // Check "name" field exists
        actions.andExpect(jsonPath("$.name").exists());

        // Check value of "name" is "India"
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    // Test 3: GET /countries/az → 404 Country not found
    @Test
    public void testGetCountryException() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/az"));

        // Check HTTP status is 404 Not Found
        actions.andExpect(status().isNotFound());

        // Check the reason phrase matches
        actions.andExpect(status().reason("Country not found"));
    }

    // Test 4: GET /countries → returns array of 4 countries
    @Test
    public void testGetAllCountries() throws Exception {
        ResultActions actions = mvc.perform(get("/countries"));

        actions.andExpect(status().isOk());

        // Check first element has code field
        actions.andExpect(jsonPath("$[0].code").exists());
    }
}