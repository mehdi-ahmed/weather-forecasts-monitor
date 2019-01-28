package com.cloudator.interview.domain;

public class Location {

    private String name;
    private String country;
    private Integer code;
    private float limit;

    public Location(String name, String country, Integer code, float limit) {
        super();
        this.name = name;
        this.country = country;
        this.code = code;
        this.limit = limit;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public float getLimit() {
        return limit;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", code=" + code +
                ", temperatureLimit=" + limit +
                '}';
    }


}
