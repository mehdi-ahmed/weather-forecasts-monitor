package com.cloudator.interview.db;

import com.cloudator.interview.domain.Forecast;
import com.cloudator.interview.repository.ForecastRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongoDbSpringIntegrationTest {

    @Autowired
    private ForecastRepository forecastRepository;

    @Test
    public void whenFindAllTemperaturesFromDBThatExceedsLimit_Expect_ListWithValidData() {

        //Check data from DB
        List<Forecast> foundCities = forecastRepository.findAll();
        assertThat(foundCities).extracting(Forecast::getCity)
                .contains("Helsinki")
                .contains("Paris")
                .contains("Oslo")
                .contains("Tunis")
                .contains("Moscow")
                .contains("Moscow");

        //All values in DB should be Exceeding = true
        for (Forecast foundCity : foundCities) {
            assertThat(foundCity.isExceed() == true);
        }

    }

}
