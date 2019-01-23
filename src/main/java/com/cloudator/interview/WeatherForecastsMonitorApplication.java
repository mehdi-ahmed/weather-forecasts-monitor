package com.cloudator.interview;

import com.cloudator.interview.domain.City;
import com.cloudator.interview.domain.Weather;
import com.cloudator.interview.service.WeatherForecastsService;
import com.cloudator.interview.util.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

import static com.cloudator.interview.util.CsvReaderConst.CITY_SAMPLE_CSV;
import static com.cloudator.interview.util.CsvReaderConst.CSV_HEADER;

@SpringBootApplication
public class WeatherForecastsMonitorApplication implements ApplicationRunner {

    @Autowired
    private WeatherForecastsService weatherForecastsService;

    public static void main(String[] args) {
        SpringApplication.run(WeatherForecastsMonitorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {

        CsvReader reader = new CsvReader();
        List<City> cities = reader.readCsv(CITY_SAMPLE_CSV, CSV_HEADER);
        Weather weatherDetailsByCity = weatherForecastsService.getWeatherDetailsByCity(2464470);
        System.out.println(weatherDetailsByCity);
    }
}

