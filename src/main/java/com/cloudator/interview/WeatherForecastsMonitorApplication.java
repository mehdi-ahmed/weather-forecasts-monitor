package com.cloudator.interview;

import com.cloudator.interview.services.impl.LocationsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableMongoRepositories
public class WeatherForecastsMonitorApplication implements ApplicationRunner {

    @Autowired
    private LocationsServiceImpl locationsService;

    public static void main(String[] args) {
        SpringApplication.run(WeatherForecastsMonitorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        locationsService.setLocations(locationsService.getAllLocations());
    }

}

