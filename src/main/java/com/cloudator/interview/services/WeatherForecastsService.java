package com.cloudator.interview.services;

import com.cloudator.interview.domain.Temperature;

import java.io.IOException;
import java.util.List;

public interface WeatherForecastsService {

    Temperature getTemperatureByCity(String cityId) throws IOException;

    List<Temperature> getTemperatureForBulkCities(String citiesId) throws IOException;
}
