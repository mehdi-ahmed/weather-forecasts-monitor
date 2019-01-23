package com.cloudator.interview.domain;

public class Weather {

    private long id;
    private String cityName;
    private Temperature temperature;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + id + '\'' +
                ", cityName='" + cityName + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
