package com.cloudator.interview.domain;

public class Location {

    private String name;
    private String country;
    private long code;
    private float temperatureLimit;

    public Location(String name, String country, long code, float temperatureLimit) {
        super();
        this.name = name;
        this.country = country;
        this.code = code;
        this.temperatureLimit = temperatureLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public float getTemperatureLimit() {
        return temperatureLimit;
    }

    public void setTemperatureLimit(float temperatureLimit) {
        this.temperatureLimit = temperatureLimit;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", code=" + code +
                ", temperatureLimit=" + temperatureLimit +
                '}';
    }


}
