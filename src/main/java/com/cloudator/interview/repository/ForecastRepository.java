package com.cloudator.interview.repository;

import com.cloudator.interview.domain.Forecast;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends MongoRepository<Forecast, Integer> {

    Forecast findByCity(String name);
}
