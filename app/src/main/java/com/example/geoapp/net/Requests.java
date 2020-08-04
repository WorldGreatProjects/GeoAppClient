package com.example.geoapp.net;

import com.example.geoapp.exceptions.BadConnectionException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class Requests {

    private final static int timeout = 10000;
    private final static String GET = "GET";
    private final static String POST = "POST";
    private final static String PUT = "PUT";
    private final static String DELETE = "DELETE";


    public static JSONObject get(String url_str) throws IOException, JSONException, BadConnectionException {
        HttpURLConnection connection = createRequest(url_str,GET);

        int responseCode = connection.getResponseCode();
        if ( responseCode != 200){
            throw new BadConnectionException( responseCode );
        }

        String response = getResponseBody( connection.getInputStream() );
        return new JSONObject(response);
    }

    public static JSONObject post(String url_str, JSONObject jsonObject) throws IOException, JSONException {
        HttpURLConnection connection = createRequest(url_str,POST);
        connection.getOutputStream().write(jsonObject.toString().getBytes());

        int responseCode = connection.getResponseCode();
        if ( responseCode != 200){
            throw new BadConnectionException( responseCode );
        }

        String response = getResponseBody( connection.getInputStream() );
        return new JSONObject(response);
    }

    public static JSONObject put(String url_str, JSONObject jsonObject) throws IOException, JSONException {
        HttpURLConnection connection = createRequest(url_str,PUT);
        connection.getOutputStream().write(jsonObject.toString().getBytes());

        int responseCode = connection.getResponseCode();
        if ( responseCode != 200){
            throw new BadConnectionException( responseCode );
        }

        String response = getResponseBody( connection.getInputStream() );
        return new JSONObject(response);
    }

    public static JSONObject delete(String url_str) throws IOException, JSONException {
        HttpURLConnection connection = createRequest( url_str, DELETE );

        int responseCode = connection.getResponseCode();
        if ( responseCode != 200){
            throw new BadConnectionException( responseCode );
        }

        String response = getResponseBody( connection.getInputStream() );
        return new JSONObject(response);
    }

    private static HttpURLConnection createRequest(String url_str, String method) throws IOException {
        URL url = new URL(url_str);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(timeout);
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        if ( ( method.equals( POST ) ) || ( method.equals( PUT ) ) ){
            connection.setDoOutput(true);
        }

        return connection;
    }

    private static String getResponseBody(InputStream stream) throws IOException {
        String str;
        StringBuilder builder = new StringBuilder();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        while ((str = bufferedReader.readLine()) != null){
            builder.append(str);
        }
        return builder.toString();
    }
}
