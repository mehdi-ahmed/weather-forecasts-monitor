package com.cloudator.interview;

import com.cloudator.interview.services.impl.LocationsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = {"com.cloudator.interview"})
public class WeatherForecastsMonitorApplication implements ApplicationRunner {

    private final LocationsServiceImpl locationsService;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public WeatherForecastsMonitorApplication(LocationsServiceImpl locationsService, MongoTemplate mongoTemplate) {
        this.locationsService = locationsService;
        this.mongoTemplate = mongoTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherForecastsMonitorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        locationsService.setLocations(locationsService.getAllLocations());
    }

}

