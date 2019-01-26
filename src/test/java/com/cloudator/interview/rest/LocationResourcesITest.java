package com.cloudator.interview.rest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LocationResourcesITest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenRetrievingAllLocationsFromCSV_ReturnValidJsonWithMatchingFields()
            throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/locations/listdata").contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status()
                .isOk())
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[0].name").value("London"))
                .andExpect(jsonPath("$[0].country").value("GB"))
                .andExpect(jsonPath("$[0].code").value("2643743"))
                .andExpect(jsonPath("$[0].limit").value("-1.0"))

                .andExpect(jsonPath("$[1].name").value("Helsinki"))
                .andExpect(jsonPath("$[1].country").value("FI"))
                .andExpect(jsonPath("$[1].limit").value("-1.0"))

                // Check the whole Json
                .andExpect(content().string("[{\"name\":\"London\",\"country\":\"GB\",\"code\":2643743,\"limit\":-1.0},{\"name\":\"Helsinki\",\"country\":\"FI\",\"code\":658225,\"limit\":-1.0},{\"name\":\"Brussels\",\"country\":\"BE\",\"code\":2800866,\"limit\":10.0},{\"name\":\"Oslo\",\"country\":\"NO\",\"code\":6453366,\"limit\":5.0},{\"name\":\"Paris\",\"country\":\"FR\",\"code\":2988507,\"limit\":8.0},{\"name\":\"Tunis\",\"country\":\"TN\",\"code\":2464470,\"limit\":15.0},{\"name\":\"Moscow\",\"country\":\"RU\",\"code\":524901,\"limit\":-5.0},{\"name\":\"Montreal\",\"country\":\"RU\",\"code\":6077243,\"limit\":0.0}]"));
    }
}
