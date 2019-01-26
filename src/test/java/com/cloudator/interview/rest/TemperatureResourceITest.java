package com.cloudator.interview.rest;

import com.cloudator.interview.domain.Temperature;
import com.cloudator.interview.services.impl.TemperatureServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TemperatureResourceITest {

    @MockBean
    private TemperatureServiceImpl temperatureService;

    @Autowired
    private MockMvc mockMvc;

    private Temperature mockTemperature;
    private LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
    String expectedTemperatureJson = "{\"id\":12345,\"cityName\":\"cityMock\",\"temp\":12.0,\"tempLimit\":13.0,\"pressure\":1014.0,\"humidity\":93.0,\"temp_min\":6.0,\"temp_max\":8.0,\"measureTime\":\"" + localDateTime + "\",\"exceeds\":true}";

    @Before
    public void init() {
        mockTemperature = createTestTemperature();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenTemperatures_whenGetBulkTemperaturesEmployees_thenStatus200()
            throws Exception {

        mockMvc.perform(get("/temperature/bulk")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenACityCode_WhenServiceIsCalled_TemperatureIsRetrieved() throws Exception {

        Mockito.when(temperatureService.getTemperatureByCityCode(Mockito.anyInt())).thenReturn(mockTemperature);

        RequestBuilder requestBuilder = get("/temperature/Helsinki").accept(
                APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(expectedTemperatureJson, result.getResponse().getContentAsString(), false);

        verify(temperatureService, times(1)).getTemperatureByCityCode(Mockito.anyInt());
        verifyNoMoreInteractions(temperatureService);
    }

    private Temperature createTestTemperature() {

        return new Temperature(12345, "cityMock", 12.0f, 13.0f, 1014
                , 93f, 6.0f, 8.0f, localDateTime, true);
    }


}
