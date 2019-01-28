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
public class LocationResourceITest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenRetrievingAllLocationsFromCSV_ReturnValidJsonWithMatchingFields()
            throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/locations").contentType(MediaType.APPLICATION_JSON);

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
                .andExpect(content().string("[{\"code\":2643743,\"name\":\"London\",\"country\":\"GB\",\"limit\":-1.0},{\"code\":658225,\"name\":\"Helsinki\",\"country\":\"FI\",\"limit\":-1.0},{\"code\":2800866,\"name\":\"Brussels\",\"country\":\"BE\",\"limit\":10.0},{\"code\":6453366,\"name\":\"Oslo\",\"country\":\"NO\",\"limit\":5.0},{\"code\":2988507,\"name\":\"Paris\",\"country\":\"FR\",\"limit\":0.0},{\"code\":2464470,\"name\":\"Tunis\",\"country\":\"TN\",\"limit\":15.0},{\"code\":524901,\"name\":\"Moscow\",\"country\":\"RU\",\"limit\":-5.0},{\"code\":6077243,\"name\":\"Montreal\",\"country\":\"RU\",\"limit\":0.0}]"));
    }


    @Test
    public void whenURLIsFak_Return404ServerError() throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("locations/listdata").contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status()
                .is4xxClientError());
    }
}
