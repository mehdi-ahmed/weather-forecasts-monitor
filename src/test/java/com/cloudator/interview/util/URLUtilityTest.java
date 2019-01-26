package com.cloudator.interview.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class URLUtilityTest {

    public static final String CITY_ID = String.valueOf(658225); // Helsinki
    public static final String PATH_SEGMENT = "weather";

    // Invalid data
    public static final String NON_EXISTING_PARAM = "non-existing-param";
    private String FAKE_API_KEY_TEST = "fake-segment";
    private String FAKE_PATH_SEGMENT_TEST = "xxxxxxxxxxxxxxxxx";


    @Value("${endpoint.url}")
    private String ENDPOINT_URL_TEST;

    @Value("${api.key}")
    private String API_KEY_TEST;

    @Test
    public void givenValidUrlParams_WhenBuildUrl_thenUrlFormatIsOk() throws URISyntaxException, IOException {

        String url = UrlUtil.buildUrl(ENDPOINT_URL_TEST, API_KEY_TEST, CITY_ID, PATH_SEGMENT);

        assertEquals(url, "https://api.openweathermap.org/data/2.5/weather?id=658225&appid=af62d6eedc4807f9dadb57fabbd073db&units=metric");

        org.assertj.core.api.Assertions.assertThat(new URI(url))
                .hasHost("api.openweathermap.org")
                .hasPath("/data/2.5/weather");

        org.assertj.core.api.Assertions.assertThat(new URI(url))
                .hasParameter("id", "658225")
                .hasParameter("appid", "af62d6eedc4807f9dadb57fabbd073db")
                .hasParameter("units", "metric")
                .hasNoParameter(NON_EXISTING_PARAM);


        // Making sure it is a valid URL with a GET call
        HttpUriRequest request = new HttpGet(url);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.OK.value()));
    }

    @Test
    public void givenInvalidApiKey_WhenBuildUrl_then401IsReceived() throws IOException {

        String url = UrlUtil.buildUrl(ENDPOINT_URL_TEST, FAKE_API_KEY_TEST, CITY_ID, PATH_SEGMENT);

        // Making sure it is a valid URL with a GET call
        HttpUriRequest request = new HttpGet(url);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    public void givenInvalidSegment_WhenBuildUrl_then404IsReceivedD() throws IOException {

        String url = UrlUtil.buildUrl(ENDPOINT_URL_TEST, API_KEY_TEST, CITY_ID, FAKE_PATH_SEGMENT_TEST);

        // Making sure it is a valid URL with a GET call
        HttpUriRequest request = new HttpGet(url);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.NOT_FOUND.value()));
    }

}
