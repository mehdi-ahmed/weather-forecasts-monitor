package com.cloudator.interview.repository;

import com.cloudator.interview.domain.Temperature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends MongoRepository<Temperature, Integer> {

    Temperature findByCityName(String name);
}
