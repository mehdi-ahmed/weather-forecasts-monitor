package com.cloudator.interview.services;

import com.cloudator.interview.domain.Forecast;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface ForecastService {

    List<Forecast> getForecastByCity(String location) throws IOException, URISyntaxException;
}
