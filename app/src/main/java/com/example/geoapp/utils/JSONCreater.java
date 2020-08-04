package com.example.geoapp.utils;

import android.util.JsonReader;

import com.example.geoapp.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;

public class JSONCreater {


    public JSONCreater(){}

    public static JSONObject login(String username , String password ) throws NoSuchAlgorithmException,
                                                                                    JSONException {
        JSONObject json = new JSONObject();
        password = Hash.getSha256(password);
        json.put("username", username);
        json.put("password", password);
        return json;
    }

    public static JSONObject resetPassByUsername(String username) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("username", username);
        return json;
    }

    public static JSONObject resetPassByEmail( String email ) throws  JSONException {
        JSONObject json = new JSONObject();
        json.put("email", email);
        return json;
    }

    public static JSONObject description(String description) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("desc", description);
        return json;
    }

    public static JSONObject followPackage(String username) throws JSONException {
        JSONObject jsonObject = actionPackage("follow");
        jsonObject.put("username" , username);
        return jsonObject;
    }

    private static JSONObject actionPackage( String action) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", action);
        return jsonObject;
    }

    public static JSONObject unFollowPackage(String username) throws JSONException {
        JSONObject jsonObject = actionPackage("unfollow");
        jsonObject.put("username" , username);
        return jsonObject;
    }
}
