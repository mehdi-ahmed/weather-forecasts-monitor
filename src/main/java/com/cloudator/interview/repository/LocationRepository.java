package com.cloudator.interview.repository;


import com.cloudator.interview.domain.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Location, Integer> {

    Location findByName(String name);
}
