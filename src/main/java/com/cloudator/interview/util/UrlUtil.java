package com.cloudator.interview.util;

import com.squareup.okhttp.HttpUrl;

import java.io.IOException;
import java.net.*;

public class UrlUtil {

    public static final String WEATHER = "weather";
    public static final String GROUP = "group";
    public static final String LIST = "list";
    private static final String ID = "id";
    private static final String APP_ID = "appid";
    private static final String UNITS = "units";
    private static final String METRIC = "metric";

    /**
     * Uses HttpUrl library to build an URL with query parameters
     *
     * @param url        read from property file
     * @param apiKey     read from property file
     * @param cityString read from CSV file
     * @return URL String
     */

    public static String buildUrl(String url, String apiKey, String cityString, String PathSegment) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addPathSegment(PathSegment);
        urlBuilder.addQueryParameter(ID, String.valueOf(cityString));
        urlBuilder.addQueryParameter(APP_ID, apiKey);

        //Get temperatures in Celsius
        urlBuilder.addQueryParameter(UNITS, METRIC);
        return urlBuilder.build().toString();
    }

    public static boolean isWeatherApiAvailable(String apiHost) throws IOException, URISyntaxException {

        URI uri = new URI(apiHost);
        String domain = uri.getHost();
        try (Socket socket = new Socket()) {
            int port = 80;
            InetSocketAddress socketAddress = new InetSocketAddress(domain, port);
            socket.connect(socketAddress, 3000);
            return true;
        } catch (UnknownHostException unknownHost) {
            return false;
        }
    }
}