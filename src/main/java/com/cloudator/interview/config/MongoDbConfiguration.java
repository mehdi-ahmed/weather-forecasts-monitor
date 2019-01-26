package com.cloudator.interview.config;

import com.cloudator.interview.repository.TemperatureRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(repositoryBaseClass = TemperatureRepository.class)
public class MongoDbConfiguration {
}
