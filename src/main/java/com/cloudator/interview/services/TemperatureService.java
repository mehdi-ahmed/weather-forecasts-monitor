package com.cloudator.interview.services;

import com.cloudator.interview.domain.Temperature;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface TemperatureService {

    Temperature getTemperatureByCityCode(Integer cityId) throws IOException, URISyntaxException;

    List<Temperature> getTemperatureForBulkCities(String citiesId) throws IOException, URISyntaxException;

    void saveLocationsWithExceedingTemperatures(Temperature temperature);

    Temperature findByName(String name);
}
