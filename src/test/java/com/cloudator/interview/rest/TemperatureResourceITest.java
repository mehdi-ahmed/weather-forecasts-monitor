package com.cloudator.interview.rest;

import com.cloudator.interview.domain.Forecast;
import com.cloudator.interview.repository.ForecastRepository;
import com.cloudator.interview.services.ForecastService;
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
import java.util.Arrays;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TemperatureResourceITest {

    @MockBean
    private ForecastService forecastService;

    @MockBean
    private ForecastRepository forecastRepository;

    @Autowired
    private MockMvc mockMvc;

    private List<Forecast> mockForecastList;

    private LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
    String expectedTemperatureJson = "[{\"code\":12345,\"city\":\"cityMock\",\"temperature\":\"12.0f\",\"limit\":\"10.0f\",\"date\":\"Today\",\"exceed\":true}]";

    @Before
    public void init() {
        mockForecastList = createTestTemperature();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenACity_whenForecast5DaysIsCalled_thenStatus200()
            throws Exception {

        mockMvc.perform(get("/forecast/Helsinki")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenACityCode_WhenServiceIsCalled_TemperatureIsRetrieved() throws Exception {

        Mockito.when(forecastService.getForecastByCity(Mockito.anyString())).thenReturn(mockForecastList);

        RequestBuilder requestBuilder = get("/forecast/Helsinki").accept(
                APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(expectedTemperatureJson, result.getResponse().getContentAsString(), false);

        Mockito.verify(forecastService, times(1)).getForecastByCity(Mockito.any());
        verifyNoMoreInteractions(forecastService);
    }

    private List<Forecast> createTestTemperature() {

        return Arrays.asList(new Forecast(12345, "cityMock", "12.0f", "10.0f", "Today", true));
    }


}