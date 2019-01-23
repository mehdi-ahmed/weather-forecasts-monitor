package com.cloudator.interview.util;

import com.squareup.okhttp.HttpUrl;

public class UrlUtil {

    public static final String WEATHER = "weather";
    public static final String ID = "id";
    public static final String APP_ID = "appid";
    public static final String UNITS = "units";
    public static final String METRIC = "metric";

    public static String buildUrl(String url, String apiKey, long cityId) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addPathSegment(WEATHER);
        urlBuilder.addQueryParameter(ID, String.valueOf(cityId));
        urlBuilder.addQueryParameter(APP_ID, apiKey);

        //Get temperatures in Celsius
        urlBuilder.addQueryParameter(UNITS, METRIC);
        return urlBuilder.build().toString();
    }

}
