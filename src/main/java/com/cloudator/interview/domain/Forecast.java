package com.cloudator.interview.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Forecast {

    private int code;
    private String city;
    private String temperature;
    private String limit;
    private String date;
    private boolean isExceed;

    public Forecast(int code, String city, String temperature, String limit, String date, boolean isExceed) {
        super();
        this.code = code;
        this.city = city;
        this.temperature = temperature;
        this.limit = limit;
        this.date = date;
        this.isExceed = isExceed;
    }


    public Forecast() {
        super();
    }

    public boolean isExceed() {
        return isExceed;
    }

    public void setExceed(boolean exceed) {
        isExceed = exceed;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "code=" + code +
                ", city='" + city + '\'' +
                ", temperature='" + temperature + '\'' +
                ", limit='" + limit + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
