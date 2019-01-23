package com.cloudator.interview.domain;

public class City {

    private String cityName;
    private String country;
    private long cityCode;
    private float temperatureLimit;

    public City(String cityName, String country, long cityCode, float temperatureLimit) {
        super();
        this.cityName = cityName;
        this.country = country;
        this.cityCode = cityCode;
        this.temperatureLimit = temperatureLimit;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getCityCode() {
        return cityCode;
    }

    public void setCityCode(long cityCode) {
        this.cityCode = cityCode;
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
                "cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                ", cityCode=" + cityCode +
                ", temperatureLimit=" + temperatureLimit +
                '}';
    }


}
