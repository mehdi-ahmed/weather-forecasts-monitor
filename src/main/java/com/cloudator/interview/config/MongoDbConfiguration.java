package com.cloudator.interview.config;

import com.cloudator.interview.repository.TemperatureRepository;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = TemperatureRepository.class)
public class MongoDbConfiguration extends AbstractMongoConfiguration {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 27017;

    @Autowired
    private Environment env;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(HOST, PORT);
    }

    @Override
    protected String getDatabaseName() {
        return env.getRequiredProperty("spring.data.mongodb.database");
    }


}
