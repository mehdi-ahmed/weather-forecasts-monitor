package com.cloudator.interview.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Temperature {

    @Id
    private Integer id;
    private String cityName;
    private float temp;
    private float tempLimit;
    private float pressure;
    private float humidity;
    private float temp_min;
    private float temp_max;
    private LocalDateTime measureTime;
    private boolean exceeds;

    public Temperature(Integer id, String cityName, float temp, float tempLimit, float pressure, float humidity
            , float temp_min, float temp_max, LocalDateTime measureTime, boolean exceeds) {
        super();
        this.id = id;
        this.cityName = cityName;
        this.temp = temp;
        this.tempLimit = tempLimit;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.measureTime = measureTime;
        this.exceeds = exceeds;
    }

    public Temperature() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTempLimit() {
        return tempLimit;
    }

    public void setTempLimit(float tempLimit) {
        this.tempLimit = tempLimit;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public LocalDateTime getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(LocalDateTime measureTime) {
        this.measureTime = measureTime;
    }

    public boolean isExceeds() {
        return exceeds;
    }

    public void setExceeds(boolean exceeds) {
        this.exceeds = exceeds;
    }
}
