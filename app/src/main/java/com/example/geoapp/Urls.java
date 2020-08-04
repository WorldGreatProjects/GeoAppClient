package com.example.geoapp;

import android.net.Uri;

import com.example.geoapp.models.Mark;

public abstract class Urls {
    public static final String API_HOST = "https://worldgreatproject.pythonanywhere.com/geo_app_main/";
    public static final String API_USER = API_HOST + "user/";
    public static final String API_USER_SUBSCRIPTIONS = API_HOST + "user/subscriptions";
    public static final String API_USER_LOGIN = API_USER + "login/";
    public static final String API_USER_REGISTRATION = API_USER + "registration/";
    public static final String API_USER_RESET_PASS = API_USER + "reset_pass/";
    public static final String API_USER_MARKS = API_USER + "mark/";
    public static final String API_USER_ACTION = API_USER + "action/";
   // public static final String API_USER_FAV_MARKS = API_USER + "fav_mark/";


    public static String deleteMarkUrl(String user_id , Mark mark){
        Uri.Builder builder = new Uri.Builder();
        return builder.authority( Urls.API_USER_MARKS)
                .appendPath(user_id)
                .appendQueryParameter("lat", String.valueOf(mark.getLatitude()))
                .appendQueryParameter("lon", String.valueOf(mark.getLatitude())).toString();
    }

}
